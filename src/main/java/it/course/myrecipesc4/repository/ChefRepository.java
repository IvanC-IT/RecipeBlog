package it.course.myrecipesc4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.course.myrecipesc4.entity.Chef;

@Repository
public interface ChefRepository extends JpaRepository<Chef, Long> {

}
