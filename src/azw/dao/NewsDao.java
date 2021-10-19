package azw.dao;

import java.util.*;

import azw.entity.*;

public class NewsDao {
	private DBHelper db = null;

	public NewsDao() {
		db = new DBHelper();
	}

	public boolean add(News news) {
		List<Object> params = new ArrayList<Object>();
		params.add(news.getCateId());
		params.add(news.getTitle());
		params.add(news.getContent());
		params.add(news.getUserid());
		return db
				.executeUpdate(
						"insert into news (cateid,title,content,userid) value (?,?,?,?)",
						params);
	}

	public boolean del(int id) {
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return db.executeUpdate("delete from news where id=?", params);
	}

	public boolean modify(News news) {
		List<Object> params = new ArrayList<Object>();
		params.add(news.getCateId());
		params.add(news.getTitle());
		params.add(news.getContent());
		params.add(news.getUserid());
		params.add(news.getId());
		return db
				.executeUpdate(
						"update news set cateid=?,title=?,content=?,userid=? where id=?",
						params);
	}

	public News getById(int id) {
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		List<News> news=null;
		News n=null;
		try {
			news=db.executeQuery("select * from news where id=?", params,News.class);
			if(news.size()==1) n=news.get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	public List<News> select(int cateid,String searchkey,Users current_user,int pageIndex/*页码*/,int pageSize){
		List<Object> params=new ArrayList<Object>();		
		String sql="select * from news";
		String w="";
		if(searchkey.length()>=0){
			params.add("%"+searchkey+"%");
			w=" title like ? ";
		}
		if(cateid>0){
			params.add(cateid);
			w=w+" and cateid=? ";
		}
		if(!current_user.getRole().equals("管理员")){
			params.add(current_user.getId());
			w=w+" and userid=? ";
			
		}		
		sql=sql+" where "+w+" limit "+(pageIndex-1)*pageSize+","+pageSize;
		System.out.println(w);
		List<News> ns=null;
		try {			
			ns = db.executeQuery(sql, params,News.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return ns;
	}
	
	public String getNickName(int userId) {
		String uname = "";
		try {
			DBHelper db = new DBHelper();
			List<Object> params = new ArrayList<Object>();
			params.add(userId);

			List<Users> us = db.executeQuery("select * from users where id=?",
					params, Users.class);
			uname = us.get(0).getNickName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uname;
	}
}
