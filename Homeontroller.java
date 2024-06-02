package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.dao.UserRepository;
import com.product.entities.User;
import com.product.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class Homeontroller {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	 private UserRepository userRepository;

	@RequestMapping("/home")
	public String home()
	{
		return "home";
	}
	
	
	@RequestMapping("/about")
	public String about()
	{
		return "about";
	}
	@RequestMapping("/")
	public String signup(Model model)
	{
		model.addAttribute("user",new User());
		return "signup";
	}
	
	//handeler for regertering user
	
	@RequestMapping(value = "/do_register",method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult result1,@RequestParam(value = "agreement",defaultValue = "false")Boolean agreeement,Model model,HttpSession session)
	{		
		try {
			if(!agreeement)
			{
				System.out.println("You have to agree terms and conditions");
				throw new Exception();
			}
			if(result1.hasErrors())
			{
				
				model.addAttribute("user",user);
				return "signup";
			}
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			
			System.out.println("Agreement " +agreeement);

			
			User result = this.userRepository.save(user);
			
			
			model.addAttribute("user",new User());
			session.setAttribute("message",new Message("Sucessfully Registered!! Please login through ur credentials", "alert-primary"));
			return "signup";
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message",new Message("Something went wrong!!"+e.getMessage(), "alert-danger"));
			
			return "signup";
		}
	}
	
	//handeler for login page
	@RequestMapping(value = "/signin",method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("user") User user,Model model,HttpSession session)
	{

		return "signup";
	}
	
	//handeler to login page
	@PostMapping("/dologin")
	public String dologin() 
	{
		
		
		return "/user/index";
	}
	

	
}
