<%@page import="com.nareen.entity.Flight"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="bookinginfo.do" method="POST">
		<h3>Available Fligts</h3>
		<br /> <br />
		<table>
			<tr>
				<th>Select</th>
				<th>Flight Number</th>
				<th>Flight Name</th>
				<th>From</th>
				<th>To</th>
				<th>Available Tickets</th>
				<th>Price</th>
			</tr>
			<%
				List<Flight> Flights =(List<Flight>)request.getAttribute("flightList");
				for(Flight flightObj:Flights){
			%>
			<tr>
				<td><input type="radio" name="mySelect" value=<%=flightObj.getId() %>></td>
				<td><%=flightObj.getFlightNumber()%></td>
				<td><%=flightObj.getFlightName()%></td>
				<td><%=flightObj.getFromLoc()%></td>
				<td><%=flightObj.getToLoc()%></td>
				<td><%=flightObj.getAvailableTickets()%></td>
				<td><%=flightObj.getTicketPrice()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<br/><br/>
	<input type="submit" value="Book"/>
	</form>

</body>
</html>