package com.bangjwo.register.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bangjwo.register.domain.entity.RegistryDocument;

public interface RegistryDocumentRepository extends MongoRepository<RegistryDocument, String> {
	Page<RegistryDocument> findByServerDataMemberId(Long memberId, Pageable pageable);
	// 기본 CRUD
}
