package com.beat.oven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beat.oven.producer.IProducerable;
import com.beat.oven.producer.entity.Channel;
import com.beat.oven.producer.entity.Chord;
import com.beat.oven.producer.entity.Note;
import com.beat.oven.producer.enums.Duration;
import com.beat.oven.producer.enums.PentatonicPitch;
import com.beat.oven.producer.enums.Velocity;


@Controller
public class HomeController {
	
	@Autowired
	private IProducerable producer;
	
	private Channel channel = new Channel(0);
	
	//@GetMapping("/home")
	@GetMapping("/")
	public String home(Model model) {
		//model.addAttribute("name", name);
		
		//debug
		Note note1 = new Note();
		note1.setPitch(PentatonicPitch.get(5).val());
		note1.setVelocity(Velocity.get(0).val());
		note1.setDuration(Duration.get(2).val());
		Chord chord1 = new Chord();
		chord1.append(note1);
		channel.append(chord1);
		
		Note note2 = new Note();
		note2.setPitch(PentatonicPitch.get(6).val());
		note2.setVelocity(Velocity.get(2).val());
		note2.setDuration(Duration.get(2).val());
		Chord chord2 = new Chord();
		chord2.append(note2);
		channel.append(chord2);
		
		Note note3 = new Note();
		note3.setPitch(PentatonicPitch.get(7).val());
		note3.setVelocity(Velocity.get(4).val());
		note3.setDuration(Duration.get(2).val());
		Chord chord3 = new Chord();
		chord3.append(note3);
		channel.append(chord3);
		
		Note note4 = new Note();
		note4.setPitch(PentatonicPitch.get(8).val());
		note4.setVelocity(Velocity.get(6).val());
		note4.setDuration(Duration.get(0).val());
		Chord chord4 = new Chord();
		chord4.append(note4);
		channel.append(chord4);		
		
		
		producer.write(channel);
		return "home";
	}
}
