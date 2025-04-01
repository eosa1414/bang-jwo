package com.bangjwo.payment.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangjwo.payment.domain.entity.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {

	Optional<Payments> findByImpUid(String impUid);

	List<Payments> findAllByMemberIdOrderByUpdatedAtAsc(Long memberId);
}
