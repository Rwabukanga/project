package ebaza.codejava.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ebaza.codejava.domain.Location;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;

@Repository
public interface LocationRepository extends IBaseRepository<Location, UUID> {

	Page<Location> findByParentIdAndStatus(UUID id, EStatus status, Pageable pageable);

	Optional<Location> findByIdAndStatus(UUID id, EStatus status);

	Page<Location> findByStatus(EStatus status, Pageable pageable);

	Page<Location> findByParentIdIsNull(Pageable pageable);

}
