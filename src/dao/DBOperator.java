package dao;

public interface DBOperator {
	public boolean add(Object object);
	public boolean delete(Object object);
	public boolean update(Object object);
	public Object get(Object object);
}
