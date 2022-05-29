package Temp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

class Book {
	public String title;
	public String author;
	public int pages;

	@Override
	public String toString() {
		return "Book{" +
				"title='" + title + '\'' +
				", author='" + author + '\'' +
				", pages=" + pages +
				'}';
	}
}

public class Solution {
//	public static void main(String[] args) throws Exception {
//    	Book book = new Book();
//    	book.title = "Обитаемый остров";
//    	book.author = "Стругацкий А., Стругацкий Б.";
//    	book.pages = 413;
//
//    	ObjectMapper mapper = new ObjectMapper();
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
//    	String jsonBook = mapper.writeValueAsString(book);
//    	System.out.println(jsonBook);
//	}
public static void main(String[] args) throws Exception {
	String jsonString = "{\"title\":\"Обитаемый остров\",\"author\":\"Стругацкий А., Стругацкий Б.\",\"pages\":413}";
	Book book = new ObjectMapper().readValue(jsonString, Book.class);
	System.out.println(book);
}
}