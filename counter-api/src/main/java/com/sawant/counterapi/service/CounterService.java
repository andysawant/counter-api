package com.sawant.counterapi.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import com.sawant.counterapi.model.WordCount;

@Service
public class CounterService {
	private static final Logger LOGGER = Logger.getLogger(CounterService.class.getName());
	
	static List<String> wordList;
	static TreeMap<String,WordCount> wordAndCountMap;
	List<WordCount> wordcountList;
	

	static {
		try (Stream<String> lines = Files.lines(Paths.get(CounterService.class.getClassLoader().getResource("Paragraph.txt").toURI()))) {
			wordList = lines.flatMap((String line) -> Stream.of(line.split("[\\p{Punct}\\s]+")))
					 .collect(Collectors.toList());
		} catch (IOException | URISyntaxException e) {
			LOGGER.fine("Unable to acces the text file.");
		}
	}
	
	public Map<String, Long> getSearchCount(List<String> searchWords) {
		Map<String,Long> countMap=new HashMap<>();
		if (searchWords != null) {
			for (String str : searchWords) {
				countMap.put(str,wordList.stream().filter(s -> s.equalsIgnoreCase(str)).count());
			}
		}
		return countMap;
	}

	public String getTopNEntries(int count) {
		Map<String, Long> originalMap = getSearchCount(wordList);
		Map<String, Long> reverseSortedMap = new LinkedHashMap<>();
		StringBuilder sb=new StringBuilder();
		originalMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(count)
				.forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
		reverseSortedMap.forEach((k,v) -> sb.append(k+"|"+v+"\n"));
		return sb.toString();
	}

	public List<WordCount> convertMapToList(Map<String, Long> searchCount) {
		List<WordCount> countList=new ArrayList<>();
		for (Map.Entry<String, Long> entry : searchCount.entrySet()) {
			countList.add(new WordCount(entry.getKey(), entry.getValue()) );
		}
		return countList;
	}
}
