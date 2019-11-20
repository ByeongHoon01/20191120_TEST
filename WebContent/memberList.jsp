<%@page import="dbpkg.MemberVO"%>
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
		ShopDAO mem_dao = ShopDAO.getInstance();
		ArrayList<MemberVO> mList = mem_dao.listMember();
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
				<td>전화번호</td>
				<td>주소</td>
				<td>가입일자</td>
				<td>고객등급</td>
				<td>거주지역</td>
			</tr>

			<%
				 for(MemberVO e : mList) {
			%>
			<tr>
			
				<td><a href="update.jsp?custno=<%=e.getCustno() %>"><%=e.getCustno() %></a></td>
				<td><%=e.getCustname() %></td>
				<td><%=e.getPhone() %></td>
				<td><%=e.getAddress() %></td>
				<td><%=e.getJoindate() %></td>
				<td><%=e.getGrade() %></td>
				<td><%=e.getCity() %></td>
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