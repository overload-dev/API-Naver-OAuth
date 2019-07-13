<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Overload - Naver_OAuth : home</title>
</head>
<body>
	<h2>hello world</h2>
	<h3>This is Naver OAuth Login sample</h3>
	<c:if test="${session_key_frame_model.sns_user_id == null}">
		<a href="/login/naver_oauth"><img height="40px" src="/assets/img/btn/naver_login_btn.PNG"></a>
	</c:if>
	<c:if test="${session_key_frame_model.sns_user_id != null}">
		<a href="/logout"><img height="40px" src="/assets/img/btn/naver_logout_btn.PNG"></a>
		<a href="/naver_userDel">delete ID</a>
	</c:if>
	
	<table border="1">
		<tr>
			<td>img</td>
			<td>id</td>
			<td>name</td>
			<td>nickname</td>
			<td>age</td>
			<td>gender</td>
			<td>birthday</td>
			<td>email</td>
		</tr>
		<tr>
			<td>
				<c:choose>
					<c:when test="${session_key_frame_model.sns_user_profile_image != null}">	
						<img src="${session_key_frame_model.sns_user_profile_image }" height="40px">
					</c:when>
					<c:otherwise>no image</c:otherwise>
				</c:choose>
			</td>
			<td>${session_key_frame_model.sns_user_id }</td>
			<td>${session_key_frame_model.sns_user_name }</td>
			<td>${session_key_frame_model.sns_user_nickname }</td>
			<td>${session_key_frame_model.sns_user_age }</td>
			<td>${session_key_frame_model.sns_user_gender }</td>
			<td>${session_key_frame_model.sns_user_birthday }</td>
			<td>${session_key_frame_model.sns_user_email }</td>
		</tr>
	</table>
</body>
</html>