package com.projectt.projectts.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.projectt.projectts.domain.BidAmountConfig;


import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;

@Repository
public interface BidConfigRepository  extends IBaseRepository<BidAmountConfig, UUID> {
 	Optional<BidAmountConfig> findByIdAndStatus(UUID id, EStatus status);
    
    List<BidAmountConfig> findByStatus(EStatus status );
    
}
