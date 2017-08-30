<%@page import="com.nareen.entity.Flight"%>
<%@page import="com.nareen.entity.Booking"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>confirmation</title>
</head>
<body>
<h3>Succeffuly booked</h3>
	<form action="welcome.do" method="get">
		<table>
			<tr>
				<th>Passenger</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Mobile</th>
			</tr>
			<%
			List<Booking> passengerList = (List<Booking>) request.getAttribute("bookingList");
			Flight flight = (Flight) request.getSession().getAttribute("flightObj");
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
			</tr>
			<%
			}
			%>
			</table>
			
Confirmation Number:<%=request.getAttribute("confirmation") %>
<br/>
Flight 	:<%=flight.getFlightName()%>::::<%=flight.getFlightNumber()%>
<br/>
<br/>
		 Want to book another flight?? 
		 <br /><br />
		 <input type="submit" value="book another flight" /> <br />

	</form>

</body>
</html>