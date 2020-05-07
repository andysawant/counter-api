package com.sawant.counterapi.model;

public class WordCount {
	
	private String word;
	private Long count;
	
	public WordCount(String str, long count2) {
		this.word=str;
		this.count=count2;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}

}
