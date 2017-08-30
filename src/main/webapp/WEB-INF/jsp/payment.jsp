<%@page import="com.nareen.entity.Flight"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User Login Page</title>
<style>
form {
	border: 3px solid #f1f1f1;
}

input[type=text], input[type=password] {
	width: 60%;
	padding: 8px 10px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

input[type=submit] {
	background-color: #4CAF50;
	color: white;
	padding: 10px 10px;
	margin: 20px 0;
	border: none;
	cursor: pointer;
	width: 50%;
}

button:hover {
	opacity: 0.8;
}
</style>
</head>
<body>
	<form action="paymentexecutor.do" method="POST">
		<label><b>Name on Card</b></label><br />
		<input type="text" placeholder="Enter CardHolder Name" name="userName" required>
		<br/> 
		<label><b>Card Number</b></label><br />
		<input type="text" placeholder="Enter Card Details" name="cardDetails"required>
		 <br/>
		 <label><b>Card CVV</b></label><br />
		 <input type="password" placeholder="Enter Card CVV" name="cardCvv"required>
		 <br/>
		 <input type="text" placeholder="Enter Exp Year" name="cardExp"required>
		 <br/>
		 <input type="submit" value="Make Payment">
	</form>
	<br />
</body>
</html>