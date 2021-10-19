package azw.dao;

import java.util.ArrayList;
import java.util.List;
import azw.dao.*;
import azw.entity.*;

public class UsersDao {
	private DBHelper db=null;
	public UsersDao(){
		db=new DBHelper();
	}
	public boolean ChangeThumb(int id,String newThumbFileName){		
		List<Object> params = new ArrayList<Object>();
		params.add(newThumbFileName);		
		params.add(id);		
		return db.executeUpdate(
						"update users set thumb=? where id=?",
						params);
	} 
	public Users getById(int id){
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		Users n = null;
		try {
			List<Users> users = db.executeQuery("select * from users where id=?",
					params, Users.class);
			if (users.size() == 1) {
				n = users.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	
}
