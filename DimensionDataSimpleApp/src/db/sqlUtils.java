package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Server;

public class sqlUtils {

	private static Connection conn;
	private static java.sql.PreparedStatement preparedStmt;

	public int insertNewServer(Server newserver) {

		preparedStmt = null;
		conn = getconnaction();
		String query = " insert into SERVER (ID, NAME, Description)" + " values (?, ?, ?)";

		try {
			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1,String.valueOf(newserver.getId()) );
			preparedStmt.setString(2, newserver.getName());
			preparedStmt.setString(3, newserver.getName());

			preparedStmt.execute();

		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		} finally {
			closeConnaction();
		}
		System.out.println("Server "+ newserver.getName() +" is now add to the servers list ");
		return newserver.getId();
	}

	public String deleteServer(String id) {

		preparedStmt = null;
		conn = getconnaction();

		String query = "delete from SERVER where id = ?";

		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, id);

			preparedStmt.execute();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		} finally {
			closeConnaction();
		}
		System.out.println("Server Number " + id + " is Deleted");
		return id;
	}

	public int serversCount() {

		ResultSet rs = null;
		Statement st = null;
		int count = 0;
		try {

			conn = getconnaction();
			st = conn.createStatement();

			rs = st.executeQuery("SELECT COUNT(*) FROM SERVER");
			while (rs.next()) {

				count = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			closeConnaction();
		}
		return count;

	}

	public void getAllServers() {

		ResultSet rs = null;
		Statement st = null;
		try {

			conn = getconnaction();
			st = conn.createStatement();

			rs = st.executeQuery("select * from SERVER");
			while (rs.next()) {
				System.out.print("ID : " + rs.getString("ID"));
				System.out.print("  -  ");
				System.out.print("Name : " + rs.getString("NAME"));
				System.out.println("");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				st.close();
				closeConnaction();
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}

		}

	}

	public String updateServer(String id, String serverName) {

		Statement st = null;
		conn = getconnaction();

		String query = "update SERVER set NAME= '" + serverName + "' where ID = '" + id + "'";
		try {

			st = conn.createStatement();
			st.execute(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			try {

				closeConnaction();
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Server is Updated ");
		return id;
	}

	private Connection getconnaction() {

		try {
			// db parameters
			String url = "jdbc:mysql://dimensiondb.c6ep2ccvi7dh.eu-west-1.rds.amazonaws.com:3306/Dimension";
			String user = "Dimension";
			String password = "Dimension1234";

			// create a connection to the database
			conn = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		return conn;
	}

	private void closeConnaction() {
		try {
			if (conn != null)
				conn.close();
			if (preparedStmt != null)
				preparedStmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			
		}
	}

}
