package edu.fsoft.spring.service;

import java.util.List;
import java.util.Optional;

import edu.fsoft.spring.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fsoft.spring.formobj.ProductFormObj;
import edu.fsoft.spring.interfaceService.IProductService;
import edu.fsoft.spring.model.Category;
import edu.fsoft.spring.model.Product;
import edu.fsoft.spring.repository.ProductRepository;
@Service
public class ProductService implements IProductService {

	@Autowired
	ProductRepository repo;

	public Optional<Product> getProductById(int id){
		return repo.findById(id);
	}

	public List<Product> listAll() {		
		return repo.findAll();
	}
	
	public List<Product> list12() {
		return repo.find12Product();
	}
	
	public List<Product> findByProductName(String name){		
		return repo.findByProductNameLike(name);
		
	}
	
	public List<Product> findByCategory(int cate){
		Category category = new Category();
		category.setId(cate);
		Product product = new Product();
		product.setCategory(category);
		return repo.findByCategory(product.getCategory());	
	}
	
	public void save(ProductFormObj form) {
		Product product = null;
		Category category = new Category();
		Supplier supplier = new Supplier();
		if (form.getId() > 0) {
			product = repo.findById(form.getId()).get();
			supplier.setId(form.getSupplier());
			category.setId(form.getCategory());
			product.setCategory(category);
			product.setDescription(form.getDescription());
			product.setPrice(form.getPrice());
			product.setProductName(form.getProductName());
			product.setImage(form.getImage());
			product.setQuantity(form.getQuantity());
		}else {
			product = new Product();
			category.setId(form.getCategory());
			supplier.setId(form.getSupplier());
			product.setSupplier(supplier);
			product.setCategory(category);
			product.setDescription(form.getDescription());
			product.setPrice(form.getPrice());
			product.setProductName(form.getProductName());
			product.setImage(form.getImage());
			product.setQuantity(form.getQuantity());
		}
		repo.save(product);
	}
	public void delete(int id) {
		repo.deleteById(id);
	}
	public Product get(int id) {
		return repo.findById(id).get();
	}

	
}
