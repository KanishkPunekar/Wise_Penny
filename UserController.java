package com.product.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.dao.ProductRepository;
import com.product.dao.UserRepository;
import com.product.entities.Product;
import com.product.entities.User;
import com.product.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	@ModelAttribute
	public void addCommonData(Model model,Principal principal)
	{
		String username = principal.getName();
		System.out.println(username);
		//get user by user name
		
		User user = this.userRepository.getUserByUserName(username);
		System.out.println("User "+user);
		model.addAttribute("user",user);
		
	}
	
	
	@RequestMapping("/index")
	public String dashbord(Model model,Principal principal)
	{
				
		model.addAttribute("title","Dashboard");

		return "normal/user_dashbord";
	}
	
	
	
	//handeler to open transactions
	@GetMapping("/Purchases")
	public String openTransactionsform(Model model)
	{
		model.addAttribute("title","Transactions");
		model.addAttribute("product",new Product());
		return "normal/Purchases";
	}
	
	
	//handeler to add products
	@PostMapping("/add_product")
	public String processProduct(@ModelAttribute Product product,Principal principal,HttpSession session)
	{
		try {
				String name = principal.getName();
				User user = this.userRepository.getUserByUserName(name);
				
				product.setUser(user);
				
				user.getProducts().add(product);
				this.userRepository.save(user);
				
				System.out.println("Data "+product);
				
				session.setAttribute("message", new Message("Your product is added !! Add more", "success"));
				
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error "+e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", new Message("Something went wrong !! Try again", "danger"));
			
		}
		return "normal/Purchases";
	}
	//show products
	
	//PER PAGE 5 DEALERS
	@GetMapping("/show/{page}")
	public String show(@PathVariable("page") Integer page,Model m,Principal principal)
	{
		m.addAttribute("title","Show Clients And Customers");
		//Customer and Client list
		String name = principal.getName();
		User user = this.userRepository.getUserByUserName(name);
		
		
		Pageable of = PageRequest.of(page, 5);
		Page<Product> products = this.productRepository.findProductsByUser(user.getId(),of);
		m.addAttribute("products", products);
		m.addAttribute("currentpage",page);
		m.addAttribute("totalPages",products.getTotalPages());
		return "normal/show_clientsandcustomers";
	}

	@GetMapping("/Sales")
	public String openSalesForm(Model model)
	{
		model.addAttribute("title","Sales");
		model.addAttribute("product",new Product());
		return "normal/Sales";
	}
	@PostMapping("/sell_product")
	public String sellProduct(@ModelAttribute Product product,Principal principal,HttpSession session)
	{
		return "";
	}
	
		
}
