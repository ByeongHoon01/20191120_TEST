<%@page import="dbpkg.PriceVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbpkg.ShopDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰 회원관리</title>
<link rel="stylesheet" href="style.css?after" type="text/css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		ShopDAO dao = ShopDAO.getInstance();
		ArrayList<PriceVO> pList = dao.listPrice();
	%>
	<header>쇼핑몰 회원관리 ver 1.0</header>
	<nav>
		<ul>
		<li><a href="insert.jsp">회원등록</a></li>
		<li><a href="memberList.jsp">회원목록조회/수정</a></li>
		<li><a href="priceList.jsp">회원매출조회</a></li>
		<li><a href="index.jsp">홈으로</a></li>
		</ul>
	</nav>

	<section>
		<h3>회원목록조회/수정</h3>
		<table border="1">
			<tr>
				<td>회원번호</td>
				<td>회원성명</td>
				<td>고객등급</td>
				<td>매출</td>

			</tr>

			<%
				for (PriceVO e : pList) {
			%>
			<tr>
				<td><%=e.getCustno()%></td>
				<td><%=e.getCustname()%></td>
				<td><%=e.getGrade()%></td>
				<td><%=e.getTotalprice()%></td>
			</tr>
			<%
				}
			%>


		</table>
	</section>
	<footer> HRDKOREA Copyright&copy;2016 All right reserved.
		Human Resource Development Service of Korea. </footer>
</body>
</html>