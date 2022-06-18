package ebaza.codejava.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ebaza.codejava.domain.Gallery;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;



@Repository
public interface GalleryRepository extends IBaseRepository<Gallery, UUID> {
	
//	List<Gallery> findByOwnerUuidAndStatus(String ownerUuid, EStatus status);

	Optional<Gallery> findByIdAndStatus(UUID id, EStatus status);

	Page<Gallery> findByStatus(EStatus status, Pageable pageable);
	    
	Page<Gallery>findByReferenceIdAndStatus(UUID referenceId, EStatus status,Pageable pageable);

}
