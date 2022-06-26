package edu.fsoft.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.fsoft.spring.model.Account;
import edu.fsoft.spring.model.Feedback;
import edu.fsoft.spring.model.News;
import edu.fsoft.spring.model.Product;
import edu.fsoft.spring.service.AccountService;
import edu.fsoft.spring.service.FeedbackService;
import edu.fsoft.spring.service.NewsService;
import edu.fsoft.spring.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	private ProductService pservice;
	@Autowired
	private NewsService nservice;
	@Autowired
	private FeedbackService fservice;
	
	@RequestMapping("/home")
	public String index(Model model) {
//		Product product = new Product();
//		model.addAttribute("product", product);
//		List<Product> listProduct = pservice.list12();
//		model.addAttribute("listProduct", listProduct);
        return "index";
    }
	
	@RequestMapping("/menu")
	public String menu(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		List<Product> listProduct = pservice.listAll();
		model.addAttribute("listProduct", listProduct);
        return "menu";
    }
	
	@RequestMapping("/about")
	public String about() {
        return "about";
    }
	
	@RequestMapping("/admin")
	public String admin() {
        return "admin-index";
    }
	@RequestMapping("/contact")
	public String contact(Model model) {
		Feedback feedback = new Feedback();
		model.addAttribute("feedback", feedback);
        return "contact";
    }
	
	@RequestMapping("/news")
	public String viewNews(Model model) {
		News news = new News();
		model.addAttribute("news", news);
		List<News> listNews = nservice.listAll();
		model.addAttribute("listNews", listNews);
        return "news";
	}
	
	@RequestMapping("/staff")
	public String staff() {
        return "staff-index";
    }
	
	 @GetMapping("/403")
	 public String error403() {
		return "/error/403";
	 }

	@RequestMapping("/test")
	public String test(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		List<Product> listProduct = pservice.listAll();
		model.addAttribute("listProduct", listProduct);
		return "listProduct";
	}
}
