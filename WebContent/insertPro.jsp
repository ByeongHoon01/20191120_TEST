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
		int result = mem_dao.insertMember(vo);
	%>
	<script type="text/javascript">
		
	<%if (result == 1) {%>
		alert("등록완료되었습니다.")
		location.href = "insert.jsp";
	<%} else {%>
		alert("등록에 실패하였습니다.")
		history.back();
	<%}%>
		
	</script>



</body>
</html>