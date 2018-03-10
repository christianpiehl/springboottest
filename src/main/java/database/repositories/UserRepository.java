package database.repositories;

import org.springframework.data.repository.CrudRepository;

import database.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
