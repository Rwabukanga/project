package com.projectt.projectts.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import com.projectt.projectts.domain.Bidding;


import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;

public interface BidRepository extends IBaseRepository<Bidding, UUID> {
	List<Bidding> findByClientIdAndStatus(String ownerUuid,EStatus status);

 	Optional<Bidding> findByIdAndStatus(UUID id, EStatus status);
    
    List<Bidding> findByStatus(EStatus status );
    
    List<Bidding> findByClientId(UUID id );
    
    List<Bidding> findByRequestIdAndStatus(String ownerUuid,EStatus status);

}