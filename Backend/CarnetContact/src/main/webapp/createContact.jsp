<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<form method="post" action="CreateContactServlet">
		<table>
			<tr>
				<th><h2>Create Contact</h2></th>
			
				<tr>
					<td><i>first name<input type="text" name="fname" size="25"></i></td>
				</tr>
				<tr>
					<td><i>last name<input type="text" name="lname" size="25"></i></td>
				</tr>
				<tr>
					<td><i>email<input type="text" name="email" size="25"></i></td>
				</tr>
				
				
				

<tr>
					<td><input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset"></td>
				</tr>
			
		</table>
	</form>
</html>