package azw.dao;

import java.util.*;

import azw.entity.*;
import azw.dao.*;

public class CategoryDao {
	private DBHelper db=null;
	public CategoryDao(){
		db=new DBHelper();
	}
	public boolean add(Categories cate) {
		List<Object> params=new ArrayList<Object>();
		params.add(cate.getCategory());		
		return db.executeUpdate("insert into categories (category) value (?)", params);

	}

	public boolean modify(Categories cate) {
		List<Object> params=new ArrayList<Object>();
		params.add(cate.getCategory());
		params.add(cate.getId());
		return db.executeUpdate("update categories set category=? where id=?", params);
	}

	public boolean delete(int id) {
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		return db.executeUpdate("delete from categories where id=?", params);
	}

	public Categories getById(int id){
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		Categories n = null;
		try {
			List<Categories> news = db.executeQuery("select * from categories where id=?",
					params, Categories.class);
			if (news.size() == 1) {
				n = news.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	public List<Categories> select() {
		List<Categories> result=null;
		try {
			result=db.executeQuery("select * from categories", null, Categories.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
