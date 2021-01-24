package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setup connection variables
		String user = "springstudent";
		String pass = "springstudent";

		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		Connection connection=null;
		
		try {
			// driver loaded
			Class.forName(driver);
			System.out.println("Driver Loaded..");
			
			// get connection to database
			try {
				PrintWriter out = response.getWriter();
				out.println("Connecting to database " + jdbcUrl);

				connection = DriverManager.getConnection(jdbcUrl, user, pass);
				System.out.println("Connection done..");

				out.println("SUCCESS!!!");
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		
		// // get connection to database
		//
		// // get connection to database
		// try {
		//
		// PrintWriter out = response.getWriter();
		// out.println("Connecting to database " + jdbcUrl);
		//
		// Class.forName(driver);
		// System.out.println("Driver loaded..");
		//
		// Connection connection = DriverManager.getConnection(user, pass, jdbcUrl);
		//
		// out.println("SUCCESS!!!");
		//
		// connection.close();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// throw new ServletException(e);
		// }

	}

}
