package com.projectt.projectts.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;


import com.projectt.projectts.domain.Request;

import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;

@Repository
public interface RequestRepository extends IBaseRepository<Request, UUID> {
	
	List<Request> findByUserIdAndStatus(String ownerUuid,EStatus status);

 	Optional<Request> findByIdAndStatus(UUID id, EStatus status);
    
    List<Request> findByStatus(EStatus status );
    
    List<Request> findByUserId(UUID id );
}
