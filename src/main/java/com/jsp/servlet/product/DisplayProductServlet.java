package com.jsp.servlet.product;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
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

@WebServlet("/display-products")
public class DisplayProductServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Map<String, Object>> product=this.fetchAllProduct();
		
		if(product!=null) {
			req.setAttribute("List", product);
			req.getRequestDispatcher("displayProduct.jsp").forward(req, resp);
		}
		else
		{
			System.err.println("no product");
		}
	}
	private List<Map<String, Object>>  fetchAllProduct() {
		
		List<Map<String, Object>>product=new ArrayList<>();
		Connection conn=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_product?user=root&password=root");
			String quary="SELECT * FROM product";
			Statement st=conn.createStatement();
			ResultSet result=st.executeQuery(quary);
			
			while (result.next()) {
				Map<String, Object> products =new HashMap<String, Object>();
				products.put("productId", result.getInt(1));
				products.put("productName", result.getString(2));
				products.put("productCompany", result.getString(3));
				products.put("productPrice", result.getInt(4));
				product.add(products);
			}
			return product;

		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;


	}

}