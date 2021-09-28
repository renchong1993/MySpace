package com.group.FakeMyspace.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group.FakeMyspace.models.Blurb;
import com.group.FakeMyspace.models.Comment;
import com.group.FakeMyspace.models.Friend;
import com.group.FakeMyspace.models.Top8;
import com.group.FakeMyspace.models.User;
import com.group.FakeMyspace.models.UserInfo;
import com.group.FakeMyspace.services.FriendService;
import com.group.FakeMyspace.services.MySpaceService;
import com.group.FakeMyspace.services.UServ;

@Controller
public class HomeController {
	
	@Autowired
	public MySpaceService mServ;
	@Autowired
	public FriendService fServ;
	@Autowired
	public UServ uServ;
	
	
	
	
	
	private static String UPLOADED_FOLDER = "src/main/resources/static/img/";
	
	
	

	
	//========== View MainPage =========//
	@GetMapping("/main/{id}")
	public String mainPage(HttpSession session, @ModelAttribute("comment")Comment comment, @ModelAttribute("blurb")Blurb blurb, @ModelAttribute("friend")Friend friend, Model viewModel, @PathVariable("id")Long uid) {
		                 //Page owner
		User user = this.uServ.findUserById(uid);		
		viewModel.addAttribute("user", user);
		
		Long loginUserId = (Long)session.getAttribute("userId");           //Login user
		User loginUser = this.uServ.findUserById(loginUserId);
		viewModel.addAttribute("loguser", loginUser);
		
		Top8 top8 = this.mServ.findTopEightOfOwner(user);
		viewModel.addAttribute("top8", top8);
		
		System.out.println("****************");
		System.out.println(user);
		System.out.println(loginUser);
		System.out.println(loginUserId);
		System.out.println(top8);
		System.out.println(user.getFriend().size());
		System.out.println("****************");
		return "Profile.jsp";
	}
	
	
	
	
	//========== Update UserInfo =========//
	@GetMapping("/editUserInfo")
	public String editUserInfo(@ModelAttribute("userInfo")UserInfo userInfo, HttpSession session, RedirectAttributes redirectAttr, Model viewModel) {
		if(session.getAttribute("userId") == null) {
			redirectAttr.addFlashAttribute("message", "You need to log in first!");
			return "redirect:/auth";
		}
		Long loginUserId = (Long)session.getAttribute("userId");           //Login user
		User loginUser = this.uServ.findUserById(loginUserId);
		
		UserInfo thisUserInfo = this.uServ.findSingleUserInfo(loginUser);
		
		viewModel.addAttribute("loguser", loginUser);
		viewModel.addAttribute("userInfo", thisUserInfo);
		
		return "EditUserInfo.jsp";
	}
	
	
	@PostMapping("/editUserInfo")
	public String userInfoUpdate(@Valid@ModelAttribute("userInfo")UserInfo userInfo, Model viewModel, HttpSession session, @RequestParam("pic")MultipartFile file,
			@RequestParam("age")int age, @RequestParam("gender")String gender, @RequestParam("location")String location, RedirectAttributes redirectAttr) {
		
		Long loginUserId = (Long)session.getAttribute("userId");           //Login user
		User loginUser = this.uServ.findUserById(loginUserId);
		viewModel.addAttribute("loguser", loginUser);
		
		if(file.isEmpty()) {
			redirectAttr.addAttribute("Message", "Missing Image");
			return "redirect:/newprod";
		}
		try {
			byte[] bytes= file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			String image_url = "/img/" + file.getOriginalFilename();
			this.uServ.updateUserInfo(gender, age, location, image_url, loginUser);
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/main/"+(Long)session.getAttribute("userId");
		
	}

	
	
	
	

	//========== Create/Edit Blurb page =========//
	@GetMapping("/editBlurb")
	public String editOrCreateBlurb(Model viewModel, HttpSession session, RedirectAttributes redirectAttr) {
		if(session.getAttribute("userId") == null) {
			redirectAttr.addFlashAttribute("message", "You need to log in first!");
			return "redirect:/auth";
		}
		Long loginUserId = (Long)session.getAttribute("userId");           //Login user
		User loginUser = this.uServ.findUserById(loginUserId);
		viewModel.addAttribute("loguser", loginUser);
		return "EditBlurb.jsp";
		
	}
	
//	@PostMapping("editBlurb/{id}")
//	public String editOrCreateBlurbProces(Model viewModel, HttpSession session, @RequestParam("pic")MultipartFile file, @RequestParam("about")String about,
//			@RequestParam("meet")String meet, @RequestParam("quote")String quote, @RequestParam("location")String location, RedirectAttributes redirectAttr) {
//		
//		Long loginUserId = (Long)session.getAttribute("userId");           //Login user
//		User loginUser = this.uServ.findUserById(loginUserId);
//		viewModel.addAttribute("loguser", loginUser);
//		
//		if(file.isEmpty()) {
//			redirectAttr.addAttribute("Message", "Missing Image");
//			return "redirect:/newprod";
//		}
//		try {
//			byte[] bytes= file.getBytes();
//			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//			Files.write(path, bytes);
//			String image_url = "/img/" + file.getOriginalFilename();
//			this.mServ.createOrEditBlurb(bid, about, meet, quote, location, image_url, loginUser);
//			
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		return "redirect:/main/"+(Long)session.getAttribute("userId");
//	}
	
	@PostMapping("editBlurb/{id}")
	public String editOrCreateBlurbProcess1(@Valid@ModelAttribute("blurb")Blurb blurb, Model viewModel, HttpSession session, BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/editBlurb";
		}
		
		Long loginUserId = (Long)session.getAttribute("userId");           //Login user
		User loginUser = this.uServ.findUserById(loginUserId);
		viewModel.addAttribute("loguser", loginUser);
		
		this.mServ.createSingleBlurb(blurb);
		return "redirect:/main/"+loginUserId;
	}
	
	
	
	//========== Create Comment MainPage =========//
	@PostMapping("/main/{id}/postComment")
	public String postComment(@PathVariable("id")Long uid, HttpSession session, @Valid@ModelAttribute("comment")Comment comment, Model viewModel, RedirectAttributes redirectAttr) {
		if(session.getAttribute("userId") == null) {
			redirectAttr.addFlashAttribute("message", "You need to log in first!");
			return "redirect:/auth";
		}
		User user = this.uServ.findUserById(uid);                          //Page owner
		viewModel.addAttribute("user", user);
		
		Long loginUserId = (Long)session.getAttribute("userId");           //Login user
		User loginUser = this.uServ.findUserById(loginUserId);
		viewModel.addAttribute("loguser", loginUser);
		
		
		this.mServ.createComm(comment);
		return "redirect:/main/"+uid;
		
	}
	
	
	//========== View All Friend Page =========//
	@GetMapping("/{id}/allfriends")
	public String allFriend(HttpSession session, @ModelAttribute("friend")Friend friend, Model viewModel, @PathVariable("id")Long uid) {
		
		User user = this.uServ.findUserById(uid);
		viewModel.addAttribute("user", user);
		
		List<Friend> allFrnd = user.getFriend();
		viewModel.addAttribute("allFrnd", allFrnd);
		
		Top8 top8 = this.mServ.findTopEightOfOwner(user);
		viewModel.addAttribute("top8", top8);
		return "AllFriend.jsp";
	}
	
	
	
	
	//========== Friend Request =========//
	//******** Need to have a validation of top8 friend quantity of a user by measuring the size of this.mServ.findTopEightOfOwner(user) ********//
	
	//create instance when user send request
	@PostMapping("/{id}/sendfriendrequest")
	public String addFriend(HttpSession session, @ModelAttribute("friend")Friend friend, Model viewModel, @PathVariable("id")Long uid, BindingResult result) {
		User user = this.uServ.findUserById(uid);                          //Page owner
		viewModel.addAttribute("user", user);
		
		Long loginUserId = (Long)session.getAttribute("userId");           //Login user
		User loginUser = this.uServ.findUserById(loginUserId);
		viewModel.addAttribute("sender", loginUser);
		
		if(result.hasErrors()) {
			return "redirect:/main/"+uid;
		}
		this.fServ.requestFriend(loginUser, user);
		return "redirect:/main/"+uid;

	}
		
	
	//View all friend request
	@GetMapping("/allfriendrequest")
	public String allFriendRequest(HttpSession session, @ModelAttribute("friend")Friend friend, Model viewModel) {
		Long loginUserId = (Long)session.getAttribute("userId");           //Login user
		User loginUser = this.uServ.findUserById(loginUserId);
		viewModel.addAttribute("receiver", loginUser);
		
		List<Friend> allReq = this.fServ.findAllRequest(loginUser);
		viewModel.addAttribute("allReq", allReq);
		
		return "AllRequest.jsp";
		
	}
	
	
	//accept friend request
	@GetMapping("/{id}/acceptRequest")
	public String acceptFriend(HttpSession session, @ModelAttribute("friend")Friend friend, Model viewModel, @PathVariable("id")Long uid, BindingResult result) {
		User user = this.uServ.findUserById(uid);                          //Page owner
		viewModel.addAttribute("user", user);

		
		Long loginUserId = (Long)session.getAttribute("userId");           //Login user
		User loginUser = this.uServ.findUserById(loginUserId);
		viewModel.addAttribute("receiver", loginUser);
		
		if(result.hasErrors()) {
			return "redirect:/allfriendrequest";
		}
		this.fServ.acceptFriendRequest(user, loginUser);
		return "redirect:/allfriendrequest";

	}

	
	
	//========== Remove a Friend from Top8 =========//
	//******** If we are changing the boolean to remove a friend from Top8, should we use @GetMapping or @PostMapping ********//
	
	//========== Delete a friend =========//
	@GetMapping("/deleteFriend/{id}")
	public String DeleteTask(@PathVariable("id")Long fid) {
		this.fServ.delete(fid);
		return "redirect:/tasks";
	}
	
	
	
	
	
}
