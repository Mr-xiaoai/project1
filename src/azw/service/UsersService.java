package azw.service;
import azw.dao.*; 
import azw.entity.*; 
public class UsersService { 
	public Users getById(int id){ 
		UsersDao usersdao=new UsersDao(); 
		return usersdao.getById(id); 
		} 
	} 