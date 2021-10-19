package azw.service;

import azw.dao.*;
import azw.entity.*;
import java.util.List;

public class NewsService {
	private NewsDao newsdao = null;

	public NewsService() {
		this.newsdao = new NewsDao();
	}

	public boolean add(News n) {
		return newsdao.add(n);
	}

	public boolean del(int id) {
		return newsdao.del(id);
	}

	public boolean edit(News n) {
		return newsdao.modify(n);
	}

	public List<News> query(int cateid, String searchkey, Users current_user,
			int pageIndex, int pageSize) {
		return newsdao.select(cateid, searchkey, current_user,pageIndex,pageSize);
	}

	public News getById(int id) {
		return newsdao.getById(id);
	}
}
