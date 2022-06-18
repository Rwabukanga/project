package ebaza.codejava.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ebaza.codejava.domain.Car;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;
@Repository
public interface CarRepository extends IBaseRepository<Car, UUID> {

	List<Car> findByOwnerUuidAndStatus(String ownerUuid, EStatus status);

	Optional<Car> findByIdAndStatus(UUID id, EStatus status);

	Page<Car> findByStatus(EStatus status, Pageable pageable);

//	List<Car> findByLocationUuidAndDeletedStatus(String locationUuid, Boolean deletedStatus);

}
