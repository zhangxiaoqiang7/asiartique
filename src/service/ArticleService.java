package service;

import java.util.Vector;

import dao.ArticleDM;
import entity.Article;

public class ArticleService {
	public boolean addArticle(Article article){
		ArticleDM articleDM=new ArticleDM();
		boolean res=articleDM.add(article);
		articleDM.close();
		return res;
	}
	public boolean delArticle(long id){
		ArticleDM articleDM= new ArticleDM();
		boolean res=articleDM.delete(id);
		articleDM.close();
		return res;
	}
	public Article getArticle(long id){
		ArticleDM articleDM=new ArticleDM();
		Article article= (Article) articleDM.get(id);
		articleDM.close();
		return article;
	}
	public Vector<Article> getAllArticles(){
		ArticleDM articleDM=new ArticleDM();
		Vector<Article> res=articleDM.getAllArticles();
		articleDM.close();
		return res;
	}
}
