package org.generation.coursecatalog.repository;

import java.util.Optional;

import org.generation.coursecatalog.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
  Optional<AppUser> findByEmail(String email);
}
