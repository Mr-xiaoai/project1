<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="azw.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="../assets/thirdparts/bootstrap-3.3.7/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../assets/thirdparts/bootstrap-3.3.7/dist/css/bootstrap-theme.min.css">
<script
	src="../assets/thirdparts/bootstrap-3.3.7/js/tests/vendor/jquery.min.js"></script>
<script
	src="../assets/thirdparts/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div style="width: 80%; margin: 0px auto">
		<center>
			<h3>文章列表</h3>
			<hr>
		</center>
		<%
		Object wayobj = request.getAttribute("way");
		Object editidobj = request.getAttribute("editid");
		Object cateobj = request.getAttribute("cate");
		String way=(String)wayobj;
		String editid=(String)editidobj;
		Object newsobj = request.getAttribute("news");
		Categories cate=(Categories)cateobj;
		News n=(News)newsobj;
		
		%>
		<form action="../news/addsave" method="post" onsubmit="doueditor()">
		<input type="hidden" value="<%=cate.getId() %>" id="cateid" name="cateid"> 
		<input type="hidden" value="<%=way %>" id="way" name="way"> 
		<input type="hidden" value="<%=editid %>" id="editid" name="editid"> 
			<div class="form-group">
				<label
					for="exampleInputEmail1">类别</label>
				<div class="dropdown">
					<button class="btn btn-default dropdown-toggle" type="button"
						id="dpbtn" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="true">
						<%=cate.getCategory() %> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
						<li><a href="#" onclick="doCateSelected(0,'所有类别')">所有类别</a></li>
						<%
							Object catesobj = request.getAttribute("cates");
															List<Categories> cates = (List<Categories>) catesobj;
															for (Categories c : cates) {
						%>

						<li><a href="#" onclick="doCateSelected(<%=c.getId()%>,'<%=c.getCategory()%>')"><%=c.getCategory()%></a></li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">标题</label> <input type="text" value="<%=n.getTitle() %>"
					class="form-control" id="title" name="title" placeholder="请输入文章标题"
					style="width: 600px; height: 30px">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">内容</label>
			<textarea rows="20" cols="50" style="display: none" id="content" name="content"></textarea> 
						<!-- 加载编辑器的容器 -->
						 <script id="xcontainer" name="xcontainer" type="text/plain">
							
						<%=n.getContent()%>	

    					</script> <!-- 配置文件 --> 
    					<script type="text/javascript" src="../assets/thirdparts/ueditor/ueditor.config.js"></script> <!-- 编辑器源码文件 --> <script
								type="text/javascript" src="../assets/thirdparts/ueditor/ueditor.all.js"></script> <!-- 实例化编辑器 -->
							<script type="text/javascript">
								var ue = UE.getEditor('xcontainer');
			
								function doueditor(){
									document.getElementById("content").value=ue.getContent();
								}
							</script>
			</div>
			<button type="submit" class="btn btn-primary" style="float:right;">提交</button>
		</form>
	</div>
	<script language="javascript">
	function doCateSelected(id,cname){
		$("#cateid").val(id);
		$("#dpbtn").html(cname+"<span class='caret'></span>");
		//document.getElementById("cateid").value=id;
	}
	</script>
</body>
</html>