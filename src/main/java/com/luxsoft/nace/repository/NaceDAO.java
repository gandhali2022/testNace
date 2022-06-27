package com.luxsoft.nace.repository;

import com.luxsoft.nace.entity.NaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NaceDAO extends JpaRepository<NaceEntity, Long> {
	
	Optional<NaceEntity> getNaceByOrder(String order);
}