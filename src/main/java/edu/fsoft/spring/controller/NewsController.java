package edu.fsoft.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.fsoft.spring.model.News;
import edu.fsoft.spring.model.Product;
import edu.fsoft.spring.service.NewsService;

@Controller
public class NewsController {
	
	
	@Autowired
	private NewsService service;
	
	//Add product
	@RequestMapping("/admin/news")
	public String showNewNewsForm(Model model) {
		News news = new News();
		model.addAttribute("news", news);
		List<News> listNews = service.listAll();
		model.addAttribute("listNews", listNews);
		return "admin-news";
	}
	
//	@RequestMapping("viewer/news")
//	public String showNews(Model model) {
//		News news = new News();
//		model.addAttribute("news", news);
//		List<News> listNews = service.listAll();
//		model.addAttribute("listNews", listNews);
//		return "news";
//	}
	
	@RequestMapping(value = "/saveNewNews", method = RequestMethod.POST)
	public String saveNews(@ModelAttribute("news") News news) {
		service.save(news);
		return "redirect:/admin/news";
	}
	
//	//List news
//	@RequestMapping("/admin/new")
//	public String viewHomePage(Model model) {
//		List<News> listNews = service.listAll();
//		model.addAttribute("listNews", listNews);
//		return "admin-new";
//	}
	//Delete News
	@RequestMapping("/deleteNews/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/admin/news";
	}
	//Edit News
	@RequestMapping("/admin/news/edit/{id}")
	public ModelAndView showEditNewsForm(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("admin-news-edit");
		News news = service.get(id);
		mav.addObject("news", news);
		return mav;
	}	
	@RequestMapping(value = "/saveNews", method = RequestMethod.POST)
	public String saveNewss(@ModelAttribute("news") News news) {
		service.save(news);
		return "redirect:/admin/news";
	}
	
	@RequestMapping("/newsDetail/{id}")
	public ModelAndView showNewsDetails(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("news-detail");
		News news = service.get(id);
		mav.addObject("news", news);
		return mav;
	}
}