package azw.service; 
import java.util.List;

import azw.dao.*; 
import azw.entity.*;
public class CategoryService { 
	public Categories getById(int id){ 
		CategoryDao catedao=new CategoryDao(); 
		return catedao.getById(id);
   } 
	public List<Categories> select(){
		CategoryDao catedao=new CategoryDao();
		return catedao.select();
	}
} 