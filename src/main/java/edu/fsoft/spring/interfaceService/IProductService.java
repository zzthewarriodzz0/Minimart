package edu.fsoft.spring.interfaceService;

import java.util.List;

import edu.fsoft.spring.formobj.ProductFormObj;
import edu.fsoft.spring.model.Product;

public interface IProductService {

	public List<Product> listAll();
	void save(ProductFormObj product);
	public void delete(int id);
	public Product get(int id);
	public List<Product> findByProductName(String name);
	public List<Product> findByCategory(int category);
}
