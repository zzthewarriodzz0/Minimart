package edu.fsoft.spring.controller;

import java.util.List;

import edu.fsoft.spring.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.fsoft.spring.formobj.ProductFormObj;
import edu.fsoft.spring.interfaceService.IOrderService;
import edu.fsoft.spring.interfaceService.IProductService;
import edu.fsoft.spring.model.Product;
@Controller
public class ProductController {

	@Autowired 
	private IProductService service;
	
	//Add product
	@RequestMapping("/newProduct")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "newProduct";
	}
	@RequestMapping(value = "/saveNewProduct", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") ProductFormObj product) {
		service.save(product);
		return "redirect:/admin/product";
	}
	//listProduct
	@RequestMapping("/admin/product")
	public String viewHomePage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "product-list";
	}
	//Delete Product
	@RequestMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/admin/product";
	}
	//Edit Product
	@RequestMapping("/editProduct/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("editProduct");
		Product product = service.get(id);
		mav.addObject("product", product);
		return mav;
	}	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct1(@ModelAttribute("product") ProductFormObj product) {
		service.save(product);
		return "redirect:/admin/product";
	}
	
	@GetMapping("/staff/order/search")
	public String searchNameUser(@RequestParam("productName") String productName, Model model) {
		if (productName.equals("")) {
			return "redirect:/staff/order";
		}
		model.addAttribute("listProducts", service.findByProductName("%"+productName+"%"));
		return "order";
	}

	@RequestMapping("/productDetail/{id}")
	public ModelAndView showProductDetails(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("product-detail");
		Product product = service.get(id);
		mav.addObject("product", product);
		return mav;
	}
}
