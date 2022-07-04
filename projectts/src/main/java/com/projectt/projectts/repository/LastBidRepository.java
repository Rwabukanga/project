package com.projectt.projectts.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;


import com.projectt.projectts.domain.LastBid;
import com.projectt.projectts.domain.Location;

import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;

@Repository
public interface LastBidRepository extends IBaseRepository<LastBid, UUID> {
	
	List<LastBid> findByClientIdAndStatus(String ownerUuid,EStatus status);

 	Optional<LastBid> findByIdAndStatus(UUID id, EStatus status);
    
    List<LastBid> findByStatus(EStatus status );
    
    List<LastBid> findByClientId(UUID id);
	Optional<LastBid> findByBidId(UUID id);
}
