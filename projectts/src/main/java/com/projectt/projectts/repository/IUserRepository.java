package ebaza.codejava.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ebaza.codejava.domain.User;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.repo.IBaseRepository;

@Repository
public interface IUserRepository extends IBaseRepository<User, UUID> {

	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User getUserByUsername(@Param("username") String username);

	public User findByUsernameAndPassword(String username, String password);

	Optional<User> findById(UUID id);

	User findByIdAndStatus(UUID id, EStatus status);

	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);

	User findByCategory(String category);

}
