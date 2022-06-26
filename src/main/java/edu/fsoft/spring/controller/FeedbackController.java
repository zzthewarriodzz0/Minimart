package edu.fsoft.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.fsoft.spring.model.Feedback;
import edu.fsoft.spring.model.News;
import edu.fsoft.spring.service.FeedbackService;

@Controller
public class FeedbackController {
 
	@Autowired
	private FeedbackService service;
	@RequestMapping(value = "/saveFeedback", method = RequestMethod.POST)
	public String saveNewss(@ModelAttribute("feedback") Feedback feedback) {
		service.save(feedback);
		return "redirect:/";
	}
}
