# Product Management System

This project is a simple Product Management System implemented using Java Servlets. The main functionality of this project is to display and add products fetched from a MySQL database.

## Project Structure

- `DisplayProductServlet.java`: The main servlet that handles HTTP GET and POST requests to display and add products.
- `displayProduct.jsp`: The JSP file that renders the list of products on the web page.

## Servlet Explanation

### DisplayProductServlet.java

This servlet is mapped to the URL pattern `/display-products` and handles HTTP GET and POST requests to display and add products.

#### Imports

```java
// ...existing code...
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
```

#### Class Declaration

```java
@WebServlet("/display-products")
public class DisplayProductServlet extends HttpServlet {
```

The `@WebServlet` annotation is used to declare the servlet and map it to the URL pattern `/display-products`.

#### doGet Method

```java
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // ...existing code...
}
```

The `doGet` method handles HTTP GET requests. It calls the `fetchAllProduct` method to retrieve the list of products from the database. If the list is not null, it sets the list as a request attribute and forwards the request to `displayProduct.jsp`. If the list is null, it prints an error message.

#### doPost Method

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String productName = req.getParameter("productName");
    String productCompany = req.getParameter("productCompany");
    int productPrice = Integer.parseInt(req.getParameter("productPrice"));
    
    boolean isAdded = this.addProduct(productName, productCompany, productPrice);
    
    if (isAdded) {
        resp.sendRedirect("display-products");
    } else {
        resp.getWriter().write("Error adding product");
    }
}
```

The `doPost` method handles HTTP POST requests to add a new product. It retrieves the product details from the request parameters and calls the `addProduct` method to insert the product into the database. If the product is added successfully, it redirects to the `display-products` URL. Otherwise, it writes an error message to the response.

#### addProduct Method

```java
private boolean addProduct(String productName, String productCompany, int productPrice) {
    Connection conn = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_product?user=root&password=root");
        String query = "INSERT INTO product (productName, productCompany, productPrice) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, productName);
        pstmt.setString(2, productCompany);
        pstmt.setInt(3, productPrice);
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return false;
}
```

The `addProduct` method connects to the MySQL database and inserts a new product using the provided details. It returns `true` if the product is added successfully, otherwise `false`.

#### fetchAllProduct Method

```java
private List<Map<String, Object>> fetchAllProduct() {
    // ...existing code...
}
```

The `fetchAllProduct` method connects to the MySQL database, executes a SQL query to fetch all products, and returns the list of products. It uses a `try-catch-finally` block to handle exceptions and ensure the database connection is closed.

## Flow of Execution

1. A client sends an HTTP GET request to the URL `/display-products`.
2. The `doGet` method of `DisplayProductServlet` is invoked.
3. The `doGet` method calls the `fetchAllProduct` method to retrieve the list of products from the database.
4. The `fetchAllProduct` method connects to the database, executes the SQL query, and returns the list of products.
5. The `doGet` method sets the list of products as a request attribute and forwards the request to `displayProduct.jsp`.
6. The `displayProduct.jsp` file renders the list of products on the web page.
7. A client sends an HTTP POST request to the URL `/display-products` with product details.
8. The `doPost` method of `DisplayProductServlet` is invoked.
9. The `doPost` method retrieves the product details from the request parameters and calls the `addProduct` method to insert the product into the database.
10. The `addProduct` method connects to the database, executes the SQL insert query, and returns `true` if the product is added successfully.
11. The `doPost` method redirects to the `display-products` URL if the product is added successfully, otherwise it writes an error message to the response.

## Servlet Interfaces and Classes

- `HttpServlet`: The base class for HTTP servlets. It provides methods to handle HTTP requests.
- `HttpServletRequest`: An interface that provides request information for HTTP servlets.
- `HttpServletResponse`: An interface that provides response information for HTTP servlets.
- `ServletException`: An exception that a servlet throws to indicate a problem.
- `IOException`: An exception that is thrown when an I/O operation fails.

## Methods

- `doGet(HttpServletRequest req, HttpServletResponse resp)`: Handles HTTP GET requests.
- `doPost(HttpServletRequest req, HttpServletResponse resp)`: Handles HTTP POST requests.
- `fetchAllProduct()`: Retrieves the list of products from the database.
- `addProduct(String productName, String productCompany, int productPrice)`: Adds a new product to the database.

## Database Connection

The database connection is established using the `DriverManager.getConnection` method with the JDBC URL, username, and password. The JDBC driver class is loaded using `Class.forName`.

## Error Handling

Exceptions are caught and printed using `e.printStackTrace()`. The database connection is closed in the `finally` block to ensure it is always closed, even if an exception occurs.

## JSP File

The `displayProduct.jsp` file is responsible for rendering the list of products on the web page. It uses the request attribute `List` to access the list of products.
