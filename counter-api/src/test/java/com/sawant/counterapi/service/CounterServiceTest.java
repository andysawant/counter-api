package com.sawant.counterapi.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.sawant.counterapi.model.WordCount;

public class CounterServiceTest {

	@InjectMocks
	private CounterService service;
	
	List<String> searchWords=new ArrayList<String>() {{
		add("123");
		add("Sed");
		add("Donec");
		add("Augue");
		add("Pellentesque");
		add("Duis");
	}};
	
	Map<String, Long> countMap = new HashMap<String,Long>() {
		{
			put("123", 0L);
			put("Sed", 16L);
			put("Donec", 8L);
			put("Augue", 7L);
			put("Pellentesque", 6L);
			put("Duis", 11L);
		}
	};
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		List<WordCount> countList=new ArrayList<WordCount>() {{
			add(new WordCount("123", 0));
			add(new WordCount("Sed", 16L));
			add(new WordCount("Donec", 8L));
			add(new WordCount("Augue", 7L));
			add(new WordCount("Pellentesque", 6L));
			add(new WordCount("Duis", 11L));
		}};
	}
	
	String topOutput="vel|17\n" + 
			"eget|17\n" + 
			"Sed|16\n" + 
			"sed|16\n" + 
			"in|15\n";

	@Test
	public void testGetSearchCountNotNull() {
		assertThat(service.getSearchCount(searchWords), is(notNullValue()));
	}
	
	@Test
	public void testGetSearchCountReturnsMap() {
		assertEquals(service.getSearchCount(searchWords), countMap);
	}
	
	@Test
	public void testGetTopNEntriesNotNull() {
		assertThat(service.getTopNEntries(5), is(notNullValue()));
	}
	
	@Test
	public void testGetTopNEntries() {
		assertEquals(service.getTopNEntries(5), topOutput);
	}
}
