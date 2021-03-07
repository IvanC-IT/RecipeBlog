package it.course.myrecipesc4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.course.myrecipesc4.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	List<Category> findByCategoryTypeOrderByCategoryIdAsc(String categoryType);

}
