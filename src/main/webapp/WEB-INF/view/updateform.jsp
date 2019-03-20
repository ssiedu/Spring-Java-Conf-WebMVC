<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<body>
	<h3>Product-Update-Form</h3>
	<hr>
	<form:form action="UpdateProduct" modelAttribute="product">
	<pre> 
		PCode	<form:input path="pcode"/>
		PName	<form:input path="pname"/>
		Price	<form:input path="price"/>
				<input type="submit" value="Change"/>
	</pre>
	</form:form>
	<hr>
	<a href="viewall">Product-List</a><br>
	<a href="index.jsp">Home</a>
</body>
</html>