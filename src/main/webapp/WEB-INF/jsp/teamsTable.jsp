<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teams Table</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h3>Teams Table JSP</h3>
		<table class="table table-striped table-bordered table-dark">
			<thead>
				<tr>
					<th>Team Name</th>
					<th>Captain</th>
					<th>City</th>
					<th>Delete</th>
					<th>Update</th>
				</tr>
			</thead>
			<c:forEach items="${teams}" var="team">
				<tr>
					<td>${team.teamName}</td>
					<td>${team.captain}</td>
					<td>${team.city}</td>
					<td><a
						href="${pageContext.request.contextPath }/deleteTeam/${team.teamId }"
						onclick="return confirm('Are you sure?')">Delete</a></td>
					<td><a
						href="${pageContext.request.contextPath }/updateTeam/${team.teamId}"
						onclick="return confirm('Are you sure?  If returned without updating your data will be lost')">Update</a></td>

				</tr>
			</c:forEach>

		</table>
		<button type="submit">
			<a href="${pageContext.request.contextPath }/exportExcel">Export
				To Excel</a>
		</button>

	</div>
</body>
</html>