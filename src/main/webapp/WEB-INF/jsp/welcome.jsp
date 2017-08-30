<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>welcome</title>
</head>
<body>
<form action="display.do" method="POST">
<h1>Welcome to AirBooking</h1>
<br/><br/>
<h4>Select the Trip</h4>
From:<br/>
<input type="text" name="from" placeholder="Enter a City or Airport"/>
<br/><br/>
To:<br/>
<input type="text" name="to" placeholder="Enter a City or Airport"/>
<br/><br/>
Select Number of Tickets:
<br/> 
<input type="text" name="noOfTickets"/>
<br/>
<input type="submit" value="submit"/>
</form>
<br/><br/>
<form action="getdetails.do" method="get">
Check flight booking details:
<input type="text" name="confirmationNumber">
<input type="submit" value="Check Booking details">
</form>
<br/><br/>
<form action="canceltickets.do" method="get">
Cancel tickets:
<input type="text" name="confirmationNumber">
<input type="submit" value="Cancel ticekts">
</form>
</body>
</html>