package ebaza.codejava.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ebaza.codejava.domain.Registrar;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;

@Repository
public interface RegistrarRepostiory extends IBaseRepository<Registrar, UUID> {

//	Optional<Registrar> findByUuid(String uuid);
//	Optional<Registrar> findById(int id);
//	Optional<Registrar> findByEmail(String email);
//	Optional<Registrar> findByUuidAndDeletedStatus(String uuid, Boolean deletedStatus);
//	Optional<Registrar> findByIdAndDeletedStatus(long id, Boolean deletedStatus);
//    List<Registrar> findByDeletedStatus(boolean b);
	
	Optional<Registrar> findByIdAndStatus(UUID id, EStatus status);
//	Optional<Registrar> findByEmail(String email);
	Page<Registrar> findByStatus(EStatus status, Pageable pageable);
}
