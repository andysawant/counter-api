package com.sawant.counterapi.controller;

import java.util.HashMap;
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
	
	@PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
	public Map<String,Object> getSearchCount(@RequestBody Map<String, Object> payload) {
		Map<String,Object> countMap=new HashMap<>();
		List<String> searchList=(List<String>) payload.get("searchText");
		countMap.put("counts", cs.getSearchCount(searchList));
		return countMap;
	}
}
