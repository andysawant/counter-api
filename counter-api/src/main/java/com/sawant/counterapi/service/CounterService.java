package com.sawant.counterapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class CounterService {
	private static final Logger LOGGER = Logger.getLogger( CounterService.class.getName() );
	static List<String> wordList;
	static {
		try (Stream<String> lines = Files.lines(Paths.get("/resources/Paragraph.txt"))) {
			wordList = lines.collect(Collectors.toList());
		} catch (IOException e) {
			LOGGER.fine("Unable to acces the text file.");
		}
	}
	
	public Map<String,Long> getSearchCount(List<String> searchWords) {
		Map<String,Long> resultMap=new HashMap<>();
		for(String str:searchWords) {
			resultMap.put(str, wordList.stream().filter(s->s.equalsIgnoreCase(str)).count());
		}
		return resultMap;
	}
	
}
