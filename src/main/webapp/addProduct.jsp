<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Enter the details of Product</h1>
	<form action="add-product" method="post">
	
		<input type="number" placeholder="Enter Product Id" name="ProductId"> <br>
		
		<input type="text" placeholder="Enter Product Name" name="ProductName"> <br>
		
		<input type="text" placeholder="Enter Product Company" name="ProductCompany"> <br>
		
		<input type="number" placeholder="Enter Product Price" name="ProductPrice"> <br>
		
		
		<input type="submit" >	
		
	
	</form>
	
	
</body>
</html>