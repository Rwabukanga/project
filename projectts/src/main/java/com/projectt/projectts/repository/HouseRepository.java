package ebaza.codejava.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ebaza.codejava.domain.House;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;

@Repository
public interface HouseRepository extends IBaseRepository<House, UUID> {

	List<House> findByOwnerUuidAndStatus(String ownerUuid, EStatus status);

	Optional<House> findByIdAndStatus(UUID id, EStatus status);

	Page<House> findByStatus(EStatus status, Pageable pageable);

	List<House> findByLocationIdAndStatus(UUID id, EStatus status);
	   

}
