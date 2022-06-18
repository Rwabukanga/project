package ebaza.codejava.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ebaza.codejava.domain.Plot;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;

@Repository
public interface PlotRepository extends IBaseRepository<Plot, UUID> {

	 List<Plot> findByOwnerUuidAndStatus(UUID id,
			 EStatus status);

	    
	    Optional<Plot> findByIdAndStatus(UUID id, EStatus status);

	    Page<Plot> findByStatus(EStatus status, Pageable pageable);
	    
	    List<Plot>findByLocationIdAndStatus(UUID id, EStatus status);
}
