<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SLiPP :: 회원가입</title>
<%@ include file="./commons/_head.jspf" %>
<style>
  body {
    padding-top: 40px;
  }
</style>

</head>
<body>
    <%@ include file="./commons/_topnav.jspf" %>
	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
				<c:choose>
					<c:when test="${empty user.userId}">
						<c:set var="actionUrl" value="/user/create" />
						<c:set var="submitValue" value="회원가입" />
						<h1>회원가입</h1>
					</c:when>
					<c:otherwise>
						<h1>정보수정</h1>
					</c:otherwise>
				</c:choose>
				
				<c:set var="actionUrl" value="/user/create" />
				<c:set var="submitValue" value="회원가입" />
				<c:if test="${not empty user.userId}">
				<c:set var="actionUrl" value="/user/update" />
				<c:set var="submitValue" value="정보수정" />
				</c:if>
				
				</div>
				<form name="user" method="post" action="${actionUrl}">
					<table>
						<tr>
							<td>사용자 아이디</td>
							<c:choose>
							<c:when test="${empty user.userId}">
								<td><input type="text" name="userId" value="${user.userId}"></td>
							</c:when>
							<c:otherwise>
								<td>${user.userId}<input type="hidden" name="userId" value="${user.userId}" /></td>
							</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="password" value="${user.password}"></td>
						</tr>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" value="${user.name}"></td>
						</tr>
						<tr>
							<td>이메일</td>
							<td><input type="text" name="email" value="${user.email}"></td>
						</tr>
					</table>
					<input type="submit" value="${submitValue}" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>