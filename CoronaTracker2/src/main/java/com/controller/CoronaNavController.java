package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.model.LocationStates;
import com.service.CovidTrackerService;

@Controller
public class CoronaNavController {
	
	@Autowired
	private CovidTrackerService covidService;
	
	@GetMapping("/")
	public String getData(Model model) {
		List<LocationStates> data = covidService.getAllStates();
		model.addAttribute("totalCases", data);
		model.addAttribute("totalLatestCases", data.stream().mapToLong(LocationStates::getLatestTotalCases).sum());
        model.addAttribute("latestNewCases", data.stream().mapToLong(LocationStates::getDiffFromPrevDay).sum());
        
        return "index";

	}
	
	@GetMapping("/chart")
	public String getImage(Model m) {
		return "chart";
	}
} 
