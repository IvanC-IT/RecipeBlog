package it.course.myrecipesc4.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.course.myrecipesc4.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByNickname(String nickname);

	boolean existsByEmail(String email);


	boolean existsByNickname(String nickname);

	Optional<User> findByNicknameOrEmail(String nickname, String email);
	
}
