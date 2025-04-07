package com.bangjwo.lock;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.multipart.MultipartFile;

import com.bangjwo.blockchain.application.service.BlockchainService;
import com.bangjwo.common.crypto.AESService;
import com.bangjwo.common.crypto.RSAService;
import com.bangjwo.contract.application.dto.request.TenantSignatureUpdateRequestDto;
import com.bangjwo.contract.application.service.ContractService;
import com.bangjwo.contract.application.service.PinataStorageService;
import com.bangjwo.contract.domain.entity.Contract;
import com.bangjwo.contract.domain.entity.TenantInfo;
import com.bangjwo.contract.domain.repository.ContractRepository;
import com.bangjwo.contract.domain.vo.ContractStatus;
import com.bangjwo.global.common.exception.BusinessException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedisLockIntegrationTest {

	@Autowired
	private ContractService contractService;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@MockitoBean
	private BlockchainService blockchainService;

	@MockitoBean
	private AESService aesService;

	@MockitoBean
	private RSAService rsaService;

	@MockitoBean
	private PinataStorageService pinataStorageService;

	@MockitoBean
	private ContractRepository contractRepository;

	@BeforeEach
	void setUp() throws IOException {
		redisTemplate.getConnectionFactory().getConnection().flushAll();

		Contract contract = mock(Contract.class);
		given(contractRepository.findByContractId(1L)).willReturn(Optional.of(contract));
		given(contract.getTenantId()).willReturn(999L);
		given(contract.getContractStatus()).willReturn(ContractStatus.TENANT_COMPLETED);

		TenantInfo tenantInfo = mock(TenantInfo.class);
		given(contract.getTenantInfo()).willReturn(tenantInfo);
	}

	@Test
	void testLockSuccess() throws Exception {
		MultipartFile multipartFile = mock(MultipartFile.class);
		given(multipartFile.getBytes()).willReturn("mock-pdf-content".getBytes());

		TenantSignatureUpdateRequestDto dto = new TenantSignatureUpdateRequestDto();
		dto.setContractId(1L);
		dto.setSignature(multipartFile);

		assertDoesNotThrow(() -> contractService.updateTenantSignature(dto, 999L));
	}

	@Test
	void testLockFail_whenKeyAlreadyLocked() {
		redisTemplate.opsForValue().set("contract:1", "LOCKED", Duration.ofSeconds(30));

		BusinessException e = assertThrows(BusinessException.class, () -> {
			contractService.updateTenantSignature(
				new TenantSignatureUpdateRequestDto() {{
					setContractId(1L);
					setSignature(mock(MultipartFile.class));
				}},
				999L
			);
		});

		assertEquals("LOCK4002", e.getErrorCode().getCode());
	}

	@Test
	void testConcurrentRedisLock() throws InterruptedException {
		int threadCount = 20;
		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		CountDownLatch startLatch = new CountDownLatch(1);
		CountDownLatch doneLatch = new CountDownLatch(threadCount);
		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failCount = new AtomicInteger(0);

		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					TenantSignatureUpdateRequestDto dto = new TenantSignatureUpdateRequestDto();
					dto.setContractId(1L);
					MultipartFile file = mock(MultipartFile.class);
					given(file.getBytes()).willReturn("mock-content".getBytes());
					dto.setSignature(file);

					startLatch.await(); // 일제히 시작
					contractService.updateTenantSignature(dto, 999L);
					successCount.incrementAndGet();
				} catch (BusinessException e) {
					failCount.incrementAndGet();
					System.out.println("[FAIL] " + e.getErrorCode().getCode());
				} catch (Exception e) {
					failCount.incrementAndGet();
					e.printStackTrace();
				} finally {
					doneLatch.countDown();
				}
			});
		}

		startLatch.countDown(); // 일제히 출발
		doneLatch.await(); // 모두 완료될 때까지 대기
		executorService.shutdown();

		System.out.println("성공: " + successCount.get());
		System.out.println("실패: " + failCount.get());

		assertEquals(1, successCount.get(), "성공한 스레드는 정확히 1개여야 합니다");
		assertEquals(threadCount - 1, failCount.get(), "나머지 스레드는 락 에러가 발생해야 합니다");
	}
}
