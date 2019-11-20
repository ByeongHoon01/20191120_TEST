<%@page import="dbpkg.MemberVO"%>
<%@page import="dbpkg.ShopDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰 회원관리</title>
<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
	<header>쇼핑몰 회원관리 ver 1.0</header>
	<nav>
	<a href="insert.jsp">회원등록</a>
	<a href="memberList.jsp">회원목록조회/수정</a>
	<a href="priceList.jsp">회원매출조회</a>
	<a href="index.jsp">홈으로</a>
	</nav>
	
	<%
		request.setCharacterEncoding("UTF-8");
		ShopDAO mem_dao = ShopDAO.getInstance();
		int custno = Integer.parseInt(request.getParameter("custno"));
		String custname = request.getParameter("custname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String joindate = request.getParameter("joindate");
		String grade = request.getParameter("grade");
		String city = request.getParameter("city");
		MemberVO vo = new MemberVO();
		vo.setCustno(custno);
		vo.setCustname(custname);
		vo.setPhone(phone);
		vo.setAddress(address);
		vo.setJoindate(joindate);
		vo.setGrade(grade);
		vo.setCity(city);
		int result = mem_dao.updateMember(vo);
	%>
	<script type="text/javascript">
		
	<%if (result == 1) {%>
		alert("회원정보수정이 완료되었습니다.")
		location.href = "memberList.jsp";
	<%} else {%>
		alert("회원정보수정에 실패하였습니다.")
		history.back();
	<%}%>
		
	</script>


</body>
</html>