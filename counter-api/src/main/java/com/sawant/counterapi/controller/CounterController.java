package com.sawant.counterapi.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sawant.counterapi.service.CounterService;

@RestController
@RequestMapping("/counter-api")
public class CounterController {

	@Autowired
	CounterService cs;
	
	@GetMapping("/")
	public String index() {
		return "welcome";
	}
	
	@PostMapping(path = "/search")
	public Map<String,Long> getSearchCount(@RequestBody List<String> searchText) {
		return cs.getSearchCount(searchText);
	}
	
}
