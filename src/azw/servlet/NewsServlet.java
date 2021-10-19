package azw.servlet;
import java.io.IOException; 
import java.util.*; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest;    import javax.servlet.http.HttpServletResponse; 
import azw.dao.*; 
import azw.entity.*; 
import azw.service.*;  
@WebServlet("/news/*") 
public class NewsServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L; 
	@Override 
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		String url=request.getRequestURI(); 
		String methodName=url.substring(url.lastIndexOf("/")+1); 
		switch(methodName){ 
		case "addface": 
			addface(request,response); 
			break; 
		case "addsave": 
			addsave(request,response); 
			break; 
		case "del": 
			del(request,response); 
			break; 
		case "edit": 
			break; 
		case "select":  
			query(request,response); 
			break; 
		default: 
			System.out.println("请求的方法没有找到"+methodName);  
				   } 
		} 
	public void query(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		request.setCharacterEncoding("utf-8");
		String searchkey=request.getParameter("searchkey");
		String pageIndex=request.getParameter("pageIndex");
		String pageSize=request.getParameter("pageSize");
		String cateid=request.getParameter("cateid");
		if(searchkey==null)searchkey="";
		if(cateid==null)cateid="-1";
		if(pageIndex==null)pageIndex="1";
		request.setAttribute("pageIndex", pageIndex); 
		if(pageSize==null)pageSize="5";
		NewsService ns=new NewsService(); 
		UsersService us=new UsersService(); 
		CategoryService cs=new CategoryService();
		List<Categories> cates=cs.select();
		request.setAttribute("cates", cates);
		Users u=us.getById(1); 
		List<News> news=ns.query(Integer.valueOf(cateid),searchkey, u,Integer.valueOf(pageIndex),Integer.valueOf(pageSize)); 
		request.setAttribute("news",news);  
   for(News n:news){ 
	   n.setCategory(cs.getById(n.getCateId()).getCategory());
	   n.setNickname(us.getById(n.getUserid()).getNickName());
	   }   
   //response.sendRedirect(arg0); 
   request.getRequestDispatcher("../admin/newsMgr.jsp").forward(request, response); 
   } 
	public void addface(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		System.out.println("现在由ADD 方法接管你的添加请求"); 
		request.setCharacterEncoding("utf-8");
		NewsService ns=new NewsService(); 
		CategoryService cs=new CategoryService();
		List<Categories> cates=cs.select();
		String way=request.getParameter("way");
		String editid=request.getParameter("editid");
		String caption="新增";
		Categories cate=new Categories("所有类别");
		News n=new News(-1,0,"","",1);
		if(way.equals("edit")){
			caption="修改";
			n=ns.getById(Integer.valueOf(editid));
			cate=cs.getById(n.getCateId());
		}
		request.setAttribute("news", n);
		request.setAttribute("cates", cates);
		request.setAttribute("cate", cate);
		request.setAttribute("way", way);  //判断修改还是新增
		request.setAttribute("editid", editid);  //修改的新闻条数
		request.getRequestDispatcher("../admin/newsMgr_edit.jsp").forward(request, response);
		}   
	public void addsave(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		System.out.println("addsave调用中...");  
		request.setCharacterEncoding("utf-8");
		NewsDao newsdao=new NewsDao();
		String way=request.getParameter("way");
		String editid=request.getParameter("editid");
		String cateid=request.getParameter("cateid");  //新闻类型的id
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String userid="1";   	//request.getParameter("userid");
		NewsService ns=new NewsService(); 
		UsersService us=new UsersService(); 
		if(way.equals("add")){
		News n=new News(Integer.parseInt(cateid),title,content,Integer.parseInt(userid));
		ns.add(n);
		}
		else{
		News n=new News(Integer.parseInt(editid),Integer.parseInt(cateid),title,content,Integer.parseInt(userid));
		ns.edit(n);
		}
		
		response.sendRedirect("./select");
		
	} 
	public void del(HttpServletRequest request, HttpServletResponse response)   
			throws ServletException, IOException {    
		String delid=request.getParameter("delid"); 
		delid=(delid==null?"0":delid);  
		NewsService ns=new NewsService(); 
		ns.del(Integer.valueOf(delid));
		response.sendRedirect("./select"); 
		System.out.println("ready to delete....."+delid); 
	} 
}