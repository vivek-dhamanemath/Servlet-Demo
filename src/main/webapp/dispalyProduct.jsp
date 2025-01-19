<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
</head>
<body>
    <h1>List Of Products</h1>
    
    <form action="display-products" method="get"></form>    

    <%
        List<Map<String, Object>> result = (List<Map<String, Object>>) request.getAttribute("list");

        if (result != null && !result.isEmpty()) {
    %>
        <table border="1px">
            <thead>
                <tr>
                    <td>Product Id</td>
                    <td>Product Name</td>
                    <td>Product Company</td>
                    <td>Product Price</td>
                </tr>
            </thead>
            <tbody>
            <%
                for (Map<String, Object> product : result) {
            %>
                <tr>
                    <td><%= product.get("id") %></td>
                    <td><%= product.get("name") %></td>
                    <td><%= product.get("company") %></td>
                    <td><%= product.get("price") %></td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p>No products available.</p>
    <%
        }
    %>
</body>
</html>