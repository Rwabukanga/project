package com.projectt.projectts.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;


import com.projectt.projectts.domain.BidPayment;


import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;

@Repository
public interface BidPaymentRepository extends IBaseRepository<BidPayment, UUID> {
	List<BidPayment> findByClientIdAndStatus(String ownerUuid,EStatus status);

 	Optional<BidPayment> findByIdAndStatus(UUID id, EStatus status);
    
    List<BidPayment> findByStatus(EStatus status );
    
    List<BidPayment> findByClientId(UUID id );
}
