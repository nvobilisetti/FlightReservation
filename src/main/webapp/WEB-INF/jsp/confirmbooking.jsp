<%@page import="java.util.List"%>
<%@page import="com.nareen.entity.Flight"%>
<%@page import="com.nareen.entity.Booking"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>check details</title>
</head>
<body>
	<form action="paymentredirection.do" methood="get">
		<%
			List<Booking> passengerList= (List<Booking>)request.getSession().getAttribute("passengerDetails");
			Flight flight = (Flight) request.getSession().getAttribute("flightObj");
			int tickets=(Integer)request.getSession().getAttribute("ticketsToBook");
		%>
		<table>
			<tr>
				<th>Passenger</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Mobile</th>
				<th>Flight Number</th>
			</tr>
			<%
			int i=0;
			for(Booking details:passengerList){
				i++;
			%>
			<tr>
			<td><%=i%></td>
			<td><%=details.getFirstName()%></td>
			<td><%=details.getLastName()%></td>
			<td><%=details.getMailId()%></td>
			<td><%=details.getContact()%></td>
			<td><%=flight.getFlightNumber()%></td>
			</tr>
			<%
			}
			%>
			</table>
		<br/>
		<br/>
	Total Booking Cost:<%=tickets*flight.getTicketPrice() %>
		<input type="submit" value="Pay"/>
	</form>
</body>
</html>