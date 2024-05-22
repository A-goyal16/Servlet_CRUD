<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="inserttable">
Enter id:-  			<input type="number" name="id"><br>
<span><% 
    String errormessage = (String) request.getAttribute("errormessage");
    if (errormessage != null) { %>
        <p style="color: red;"><%= errormessage %></p>
<% } %></span>
Enter name:-			<input type="text" name="name"><br>
Enter age:-				<input type="number" name="age"><br>
Enter dob:-				<input type="date" name="dob"><br>
Enter qualification:-	<input type="text" name="qualification"><br>
<input type="submit">
</form>
<div>
<a href="index.html">Back to home</a>
</div>
</body>
</html>