package it.course.myrecipesc4.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.course.myrecipesc4.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
	
	Optional<Tag> findByTagName(String tagName);
}
