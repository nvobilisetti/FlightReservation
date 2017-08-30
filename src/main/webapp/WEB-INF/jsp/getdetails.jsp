<%@page import="java.util.List"%>
<%@page import="com.nareen.entity.Booking"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>booking details</title>
</head>
<body>
	<h1>Booking details</h1>
	<%
	List<Booking> bookingDetails =(List<Booking>) request.getAttribute("requestedList");
	%>
	<table>
			<tr>
				<th>Passenger</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Mobile</th>
				<th>Flight Number</th>
				<th>Confirmation Number</th>
			</tr>
			<%
			int i=0;
			for(Booking details:bookingDetails){
				i++;
			%>
			<tr>
			<td><%=i%></td>
			<td><%=details.getFirstName()%></td>
			<td><%=details.getLastName()%></td>
			<td><%=details.getMailId()%></td>
			<td><%=details.getContact()%></td>
			<td><%=details.getFlightNumber()%></td>
			<td><%=details.getConfirmation()%></td>
			</tr>
			<%
			}
			%>
			</table>
	<br />
	<br />

<a href="http://localhost:8080/airbooking/welcome.do">Check for another details</a>


</body>
</html>