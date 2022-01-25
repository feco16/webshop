package org.webshop.usermanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.webshop.usermanagement.model.AppUser;

public interface WriteUserRepository extends CrudRepository<AppUser, Integer> {
}
