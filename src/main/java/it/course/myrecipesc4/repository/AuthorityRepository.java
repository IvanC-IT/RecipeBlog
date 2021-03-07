package it.course.myrecipesc4.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.course.myrecipesc4.entity.Authority;
import it.course.myrecipesc4.entity.AuthorityName;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{

	Optional<Authority> findByName(AuthorityName roleReader);


}
