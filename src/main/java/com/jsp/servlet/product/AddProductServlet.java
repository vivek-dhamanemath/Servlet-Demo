package com.jsp.servlet.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int productId = Integer.parseInt(req.getParameter("ProductId"));

		String productName = req.getParameter("ProductName");

		String productCompany = req.getParameter("ProductCompany");

		int productPrice = Integer.parseInt(req.getParameter("ProductPrice"));

		// JDBC Connection

		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_product?user=root&password=FE321869");

			PreparedStatement pst = conn.prepareStatement("INSERT INTO products VALUES(?,?,?,?)");

			pst.setInt(1, productId);
			pst.setString(2, productName);
			pst.setString(3, productCompany);
			pst.setInt(4, productPrice);

			pst.executeUpdate();

			resp.sendRedirect("index.jsp");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
