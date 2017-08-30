<%@page import="java.util.concurrent.Future"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>processing</title>
</head>
<script type="text/javascript">
function autosubmit() {
	  document.getElementById("form").submit();
	}
	window.setInterval("autosubmit()",1000);
</script>
<body>
<form action="processconfirmation.do" id="form" method="get">
<h2>Redirecting to confirmation page </h2>

</form>
</body>
</html>