package ebaza.codejava.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import ebaza.codejava.domain.BiddingRequest;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;;

@Repository
public interface BiddingRepository extends IBaseRepository<BiddingRequest, UUID> {
	
	List<BiddingRequest> findByUserIdAndStatus(String ownerUuid,EStatus status);

 	Optional<BiddingRequest> findByIdAndStatus(UUID id, EStatus status);
    
    List<BiddingRequest> findByStatus(EStatus status );
    
    List<BiddingRequest> findByUserId(UUID id );
}
