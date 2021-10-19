<%@ page import="java.util.*"%>
<%@page import="azw.entity.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章列表</title>

<!--需要这个网址才能登入：http://localhost:8088/day11/news/select  -->

<link rel="stylesheet"
	href="../assets/thirdparts/bootstrap-3.3.7/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../assets/thirdparts/bootstrap-3.3.7/dist/css/bootstrap-theme.min.css">
<script
	src="../assets/thirdparts/bootstrap-3.3.7/js/tests/vendor/jquery.min.js"></script>

<script
	src="../assets/thirdparts/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
<script src="../assets/thirdparts/layer/layer.js"></script>
</head>
<body>
	<div style="width: 80%; margin: 0px auto">
		<center>
			<h3>文章列表</h3>
			<hr>
		</center>
		<div class="btn-group" role="group" aria-label="...">
			<button type="button" class="btn btn-default"
				onclick="doadd('add',-1)">新增</button>
			<button type="button" class="btn btn-default" onclick="doquery()">查询</button>
			<button type="button" class="btn btn-default">返回</button>
		</div>
		<table class="table table-hover" style='width: 100%'>
			<thead>
				<tr>
					<th style='width: 10%'>序号</th>
					<th style='width: 18%'>类别</th>
					<th style="width: 40%">标题</th>
					<th style="width: 18%">发布人</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<%
					Object newsobj=request.getAttribute("news");
							List<News> news=(List<News>)newsobj;
							for(News n:news){
				%>

				<tr>

					<th scope="row"><%=n.getId()%></th>
					<td><%=n.getCategory()%></td>
					<td><%=n.getTitle()%></td>
					<td><%=n.getNickname()%></td>
					<td>
						<button type="button" class="btn btn-danger"
							onclick="dodel(<%=n.getId()%>)">删除</button>&nbsp;
						<button type="button" class="btn btn-primary"
							onclick="doadd('edit',<%=n.getId()%>)">修改</button>
					</td>
				</tr>

				<%
					}
				%>
			</tbody>
		</table>
		<%
			Object po=request.getAttribute("pageIndex");
				if(po==null)po=1;
				int pageindex=Integer.valueOf(po.toString());
				int pre=pageindex-1;
				if(pre<=0)pre=1;
				int next=pageindex+1;
				if(next>=pageindex)
		%>
		<div style="text-align: right">
			<a href="../news/select?pageIndex=<%=pre%>">上一页</a>&nbsp;&nbsp; <a
				href="#">当前页：<%=pageindex%></a> <a
				href="../news/select?pageIndex=<%=next%>">下一页</a>
		</div>
	</div>

	<script language="javascript">    function dodel(id){ 
		if(confirm("are you sure want to delete this row?"))
		{ 
			location.href="../news/del?delid="+id;    
			} 
		} 
	function doadd(way,id){
		location.href="../news/addface?way="+way+"&editid="+id;
	}
	function doquery(){
		layer.open({
			type:1,
			skin:'layui-layer-rim',
			area:['600px','400px'],
			content:$("#querylayer").html()
		})
	}
	</script>
	<script language="javascript"> 
	function doCateSelected(id,cname){ 
		//alert(id+"=="+cname); 
		$(".layui-layer #cateid").val(id); 
		$(".layui-layer #dpbtn").html(cname+" <span class='caret'></span>"); 
		} 
	</script>
	<div class="querylayer" id="querylayer" style="display: none">

		<form action="../news/select" method="post">

			<table align=center>
				<tr>
					<td colspan=2 style="text-align: center">查询参数设置</td>
				</tr>
				<tr>

					<td>

						<div class="form-group">
							<input type="hidden" value="0" id="cateid" name="cateid">
							<div class="dropdown">
								<button class="btn btn-default dropdown-toggle" type="button"
									id="dpbtn" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="true">
									所有类别 <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									<li><a href="#" onclick="doCateSelected(0,'所有类别')">所有类别</a></li>
									<%
										Object catesobj = request.getAttribute("cates");
										List<Categories> cates = (List<Categories>) catesobj;
										for (Categories c : cates) {
									%>

									<li><a href="#"
										onclick="doCateSelected(<%=c.getId()%>,'<%=c.getCategory()%>')"><%=c.getCategory()%></a></li>
									<%
										}
									%>
								</ul>
							</div>
						</div>

					</td>
				</tr>
				<br>
				<br>
				<tr>
					<td>关键词:</td>
					<td><input type="text" value="" name="searchkey"
						id="searchkey" class="form-control"></td>
				</tr>

				<tr>
					<td colspan=2><br> <input type="submit" value="查询"
						class="btn btn-primary"> &nbsp; <input type="button"
						value="返回" class="btn btn-primary"
						onclick='javascript:document.getElementById("querylayer").style.display="blcok";'>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>