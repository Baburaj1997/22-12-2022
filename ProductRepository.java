package com.spring.data.rest.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.data.rest.model.Product;

public interface ProductRepository extends JpaRepository <Product, Integer>{

	public List<Product> findProductByName(String productName);

	@Query(value= "select * from products where price between ? and ?;")
	public List<Product> getByBetweenPrice(double iPrice, double oPrice);
	
	@Query(value = "SELECT p FROM Product p ORDER BY price")
	public List<Product> SortedByPrice();		  
	   
	@Query(value = "SELECT p FROM Product p ORDER BY name")
	public List<Product> SortedByName();
	   
	@Query("from Product" )
	List<Product> findProducts(Pageable pageable);
	
}
