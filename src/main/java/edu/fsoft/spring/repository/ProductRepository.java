package edu.fsoft.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.fsoft.spring.model.Category;
import edu.fsoft.spring.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query(value="Select top(12) * from product order by id desc", nativeQuery = true)
	List<Product> find12Product();
	
	List<Product> findByProductNameLike(String name);
	
	List<Product> findByCategory(Category category);
}
