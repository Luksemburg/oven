package com.beat.oven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beat.oven.producer.IProducerable;
import com.beat.oven.producer.entity.Channel;


@Controller
public class HomeController {
	
	@Autowired
	private IProducerable producer;
	
	private Channel channel = new Channel(0);
	
	//@GetMapping("/home")
	@GetMapping("/")
	public String home(Model model) {
		//model.addAttribute("name", name);
		producer.write(channel);
		return "home";
	}
}
