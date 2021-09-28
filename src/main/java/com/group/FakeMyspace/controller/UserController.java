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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group.FakeMyspace.models.Message;
import com.group.FakeMyspace.models.User;
import com.group.FakeMyspace.services.MySpaceService;
import com.group.FakeMyspace.services.UServ;



@Controller
public class UserController {
	
	@Autowired
	private  UServ uServ;
	
	@Autowired
	private  MySpaceService mServ;
	
	
	
	
	private static String UPLOADED_FOLDER = "src/main/resources/static/img/";
	
	
	

	@GetMapping("/")
	public String index(Model viewModel) {
		return "index.jsp";
	}
	
	@GetMapping("/auth")
	    public String home(@ModelAttribute("user") User user) {
	    	return "logReg.jsp";
	    }
	
	
	//================== Register =================//    
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
	    
	    System.out.println(usr);
	    
	    this.uServ.createUserInfo(null, 0, null, "src/main/resources/static/img/default_img", usr);
	    this.mServ.createDefaultBlurb("Welcome to my space!", "Friends!", ";-)", usr);
	  
	    return "redirect:/main/"+usr.getId();
	    }
	}
	
	
	//================== Login =================//
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
    	boolean isAuth = uServ.authenticateUser(email, password);
    		if(isAuth) {
    			User usr= uServ.findByEmail(email);
    			session.setAttribute("userId", usr.getId());
    			return "redirect:/main/"+usr.getId();
    		}
    		else {
    			model.addAttribute("error", "Invalid Creds, Try Again");
    			return "logReg.jsp";
    		}
    	
    	
    }
    
    
	
	//================== edit =================//
    @PostMapping('/edit/thing')
    public String logi(@RequestParam("thing_to_edit") String edit_thing, @RequestParam("hidden") String hidden, Model model, HttpSession session) {
    	boolean isAuth = uServ.authenticateUser(email, password);

 
    	User usr= uServ.findByEmail(email);

    	if (hidden == 'email') {
    		//EDIT EMAIL  user.setemail(thing_to_edt
    	} else if (hidden == 'birthday') {
    		//EDIT BIRTHDAY usre.setbirthdsay(this_to_edit)
    	}
    			
    	usr.save()
;    	
    }
    
    
    //================== Logout =================//
  	@GetMapping("/logout")
  	public String logout(HttpSession session, RedirectAttributes redirectAttr) {
  		redirectAttr.addFlashAttribute("message", "You have been successfully logged out!");
  		session.invalidate();
  		return "redirect:/auth";
  	}

	

	    
	    
	    
    //=============================Post Mapping to the Message. Needs a link to this in the profile.jsp====================================//
    public Long userSessionId(HttpSession session) {
		if(session.getAttribute("user___Id") == null) {
			return null;
		}
		return (Long)session.getAttribute("user___Id");
	}
    
    @PostMapping("/main/{id}/message") 
	public String Create(@Valid @ModelAttribute("message") Message message, BindingResult result, Model viewModel, HttpSession session, @RequestParam("name") String name, @RequestParam("content") String content) {
		if(result.hasErrors()) {
			Long userId = this.userSessionId(session);
			User user = this.uServ.findUserById(userId);
			viewModel.addAttribute("user", user);
			return "profile.jsp";
		}
		Long userId = this.userSessionId(session);
		User user = this.uServ.findUserById(userId);
		message.setSender(user);
		this.Create(message, result, viewModel, session, name, content);
		return "redirect:/main/{id}/message/sent";
	}
    
    @GetMapping("/main/{id}/message/sent")
    public String Sent() {
    	return "sent.jsp";
    }

    
    
    
    
}
