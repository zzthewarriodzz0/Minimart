package edu.fsoft.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.fsoft.spring.interfaceService.IAccountService;
import edu.fsoft.spring.model.Account;
import edu.fsoft.spring.model.News;
import edu.fsoft.spring.service.AccountService;

@Controller
public class StaffController {
	@Autowired
	private AccountService aservice;
	
	@Autowired
	private IAccountService iaccountService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/admin/staff")
	public String staffManager(Model model) {
		Account account = new Account();
		model.addAttribute("account", account);
		List<Account> listStaff = aservice.findByRole("STAFF");
		model.addAttribute("listStaff", listStaff);
		return "admin-staff";
	}
	
	@RequestMapping(value = "/saveNewStaff", method = RequestMethod.POST)
	public String saveStaff(@ModelAttribute("account") Account account) {
		if(iaccountService.findByUsername(account.getUsername()) == null) {
			account.setRole("STAFF");
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			iaccountService.save(account);	
		}
		return "redirect:/admin/staff";
	}
	
	@RequestMapping("admin/deleteStaff/{id}")
	public String deleteStaff(@PathVariable(name = "id") long id) {
		aservice.delete(id);
		return "redirect:/admin/staff";
	}
}
