package caio.desafiosantanderdio.repository;

import caio.desafiosantanderdio.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
