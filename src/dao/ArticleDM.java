package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.Article;

public class ArticleDM implements DBOperator{
	
	private Connection connect;

	public ArticleDM() {
		this.connect=new ConnManager().getConn();
	}
	@Override
	public boolean add(Object object) {
		if(object==null) return false;
		Article article=(Article)object;
		String sql="insert into article(id,title,date,author) values(?,?,?,?)";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, article.getId());
			psmt.setString(2, article.getTitle());
			psmt.setString(3, article.getDate());
			psmt.setString(4, article.getAuthor());
			int res=psmt.executeUpdate();
			if(res==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean delete(Object object) {
		if(object==null) return false;
		long id=(long)object;
		String sql="delete from article where id=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			int res=psmt.executeUpdate();
			if(res!=0) return true; 
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean update(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(Object object) {
		if(object==null) return null;
		long id=(long)object;
		String sql="select * from article where id=?";
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			psmt.setLong(1, id);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()){
				Article article=new Article();
				article.setId(rs.getLong("id"));
				article.setTitle(rs.getString("title"));
				article.setAuthor(rs.getString("author"));
				article.setDate(rs.getString("date"));
				return article;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<Article> getAllArticles(){
		String sql="select * from article";
		Vector<Article> articles=new Vector<Article>();
		try {
			PreparedStatement psmt=connect.prepareStatement(sql);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()){
				Article article=new Article();
				article.setId(rs.getLong("id"));
				article.setTitle(rs.getString("title"));
				article.setAuthor(rs.getString("author"));
				article.setDate(rs.getString("date"));
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return articles;
	}
	public void close(){
		try {
			connect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
