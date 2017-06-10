package com.caveofprogramming.spring.web.controller;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.service.OffersService;

@Controller
public class HomeController {
	
	@Autowired
	private OffersService offersService;

	private static Logger logger;
	static {
		logger = logger.getLogger(HomeController.class);
	}


	@RequestMapping("/")
	public String showHome(Model model, Principal principal) {

		List<Offer> offers = offersService.getCurrent();

		model.addAttribute("offers", offers);
		
		
		boolean hasOffer = false;
		if (principal!=null) {
			hasOffer = offersService.hasOffer(principal.getName());
		}
		
		model.addAttribute("hasOffer", hasOffer);

		/* logger.info("Showing home page..."); */
		return "home";
	}

}