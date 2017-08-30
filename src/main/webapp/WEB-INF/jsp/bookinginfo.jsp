<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="confirmation.do" method="post">
<h4>Enter User Information</h4>
<br/><br/>
<table>
			<tr>
				<th>Passenger</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Mobile</th>
			</tr>
			<%
			int passengers=(Integer)request.getSession().getAttribute("ticketsToBook");
			for(int i=1;i<=passengers;i++){
			%>
			<tr>
			<td><%=i%></td>
			<td><input type="text" name="firstName<%=i%>" placeholder="Enter First Name" /></td>
			<td><input type="text" name="lastName<%=i%>" placeholder="Enter Last Name" /></td>
			<td><input type="text" name="mailId<%=i%>" placeholder="Enter email" /></td>
			<td><input type="text" name="phNumber<%=i%>" placeholder="Enter mobile Number" /></td>
			</tr>
			<%
			}
			%>
			</table>
		<input type="submit" value="Submit" />
		<br/><br/>
	</form>

</body>
</html>