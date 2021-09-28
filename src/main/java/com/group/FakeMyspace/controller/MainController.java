package com.group.FakeMyspace.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.group.FakeMyspace.models.User;
import com.group.FakeMyspace.services.UServ;



@Controller
public class MainController {
	
	private final UServ uServ;
    
	public MainController(UServ uServ) {
		this.uServ = uServ;
		}
	
	
	//	@Autowired
//	private  UServ uServ;
	
	
	
	
	private static String UPLOADED_FOLDER = "src/main/resources/static/img/";
	
	
	

	@GetMapping("/")
	public String index(Model viewModel) {
		return "index.jsp";
	}
	
	 @GetMapping("/auth")
	    public String home(@ModelAttribute("user") User user) {
	    	return "logReg.jsp";
	    }
	    
	@PostMapping("/auth")
	public String re(@Valid @ModelAttribute("user") User user, BindingResult rslt, HttpSession session) {
		String password = user.getPassword();
	    String confirmPassword = user.getPasswordConfirm();
	    if(rslt.hasErrors()) {
	    	return "logReg.jsp";
	    }
	    else if(!password.equals(confirmPassword)){
	    	return "logReg.jsp";
	    }
	    else {
	    User usr = uServ.registerUser(user);
	    session.setAttribute("userId", usr.getId());
	    return "redirect:/main/{id}";
	    }
	}
	
	    @PostMapping("/login")
	    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
	    	boolean isAuth = uServ.authenticateUser(email, password);
	    		if(isAuth) {
	    			User usr= uServ.findByEmail(email);
	    			session.setAttribute("userId", usr.getId());
	    			return "redirect:/main/{id}";
	    		}
	    		else {
	    			model.addAttribute("error", "Invalid Creds, Try Again");
	    			return "logReg.jsp";
	    		}
	    	
	    	
	    }
	
	
	
	
}
