package com.example.NetBanking.Controller;

import java.sql.SQLException;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.NetBanking.Database.DatabaseHandler;
import com.example.NetBanking.Database.Exceptions.UserNotFoundException;

@Controller
public class MyController {
	
	DatabaseHandler db = new DatabaseHandler();
	
	@RequestMapping("/")
	public String getFirstPage() {
		return "home.html";
	}
	
	@RequestMapping("/signin")
	public String getSignIn() {
		return "signin.html";
	}
	@RequestMapping("/verifySignIn")
	public ModelAndView verifyUserMv(String user, String password) throws UserNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		if(db.verifyUserDetails(user,password)) {
			mv.addObject("uname",user);
			mv.setViewName("Welcome.jsp");
		}
		else {
			mv.setViewName("failure.jsp");
		}
		return mv;
	}
	@RequestMapping("/netbanking")
	public String getNetBanking() {
		return "Netbanking.html";
	}
	@RequestMapping("/signup")
	public String getSignUp() {
		return "signup.jsp";
	}
	@RequestMapping("/verifySignUp")
	public ModelAndView verifyUserMv( int customerId,  String user, 
	                                  String password, String cpassword) throws SQLException {
	    ModelAndView mv = new ModelAndView();

	    Pair<Boolean, String> result = db.handleSignUp(customerId, user, password, cpassword);

	    if (result.getLeft()) {
	        mv.addObject("username", result.getRight()); // Pass username to welcome page
	        mv.setViewName("Welcome.jsp");
	    } else {
	        mv.addObject("errorMessage", result.getRight());
	        mv.addObject("customerId", customerId);
	        mv.addObject("user", user);
	        mv.setViewName("signup.jsp");
	    }

	    return mv;
	}


}
