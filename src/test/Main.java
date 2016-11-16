package test;

import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import entity.Article;

public class Main {
	public static void main(String[] args) {
		Vector<Article> articles=new Vector<Article>();
		for (int i = 0; i < 50000; i++) {
			Article article=new Article();
			article.setId(new Date().getTime());
			article.setDate("2012-9-1");
			article.setTitle("how do you turn this on");
			article.setAuthor("jack rose haha");
			articles.add(article);
			
		}
		Scanner cin=new Scanner(System.in);
		cin.next();
	}
}
