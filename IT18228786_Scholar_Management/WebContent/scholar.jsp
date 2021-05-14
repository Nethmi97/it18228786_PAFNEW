<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.Scholar"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Scholar Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">

</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Scholar Management</h1>
				<form id="formItem" name="formItem">
					Research ID: <input id="researchID" name="researchID" type="text"
						placeholder="Enter Research ID"
						class="form-control form-control-sm"> <br> Progress:
					<input id="progress" name="progress" type="text"
						placeholder="Enter progress rate"
						class="form-control form-control-sm"> <br> Comment: <input
						id="comment" name="comment" type="text"
						placeholder="Enter Comments" class="form-control form-control-sm">
					<br>
					approval : <input id="approval" name="approval" type="text"
						placeholder="Enter approval"
						class="form-control form-control-sm"> <br>
						<br> <input id="btnSave" name="btnSave" type="button"
					value="Save" class="btn btn-primary"> <input type="hidden"
					id="hidItemIDSave" name="hidItemIDSave" value=""> <br>
					
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<div id="divItemsGrid">
					<%
					Scholar scholarObj = new Scholar();
					out.print(scholarObj.readresearchstatus());
					%>
				</div>
			</div>
		</div>
	</div>
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/scholars.js"></script>
</body>
</html>