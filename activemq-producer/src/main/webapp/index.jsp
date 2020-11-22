<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<form action="${pageContext.request.contextPath }/sendMessage"  method="post">
			
			收件人：<input type="text" name="to" /><br />
			主题：<input type="text" name="subject"><br />
			正文：<textarea rows="3" cols="22" name="content"></textarea><br />
			<input type="submit" value="发送">&nbsp;<input type="reset" value="重置">
		</form>
	
	</center>
</body>
</html>