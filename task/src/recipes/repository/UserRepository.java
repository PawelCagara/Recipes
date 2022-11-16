package recipes.repository;

import org.springframework.data.repository.CrudRepository;
import recipes.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailIgnoreCase(String email);
}
