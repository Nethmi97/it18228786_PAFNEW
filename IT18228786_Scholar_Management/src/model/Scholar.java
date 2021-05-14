package model;

import java.sql.*;

//business logic and db handling implementation
public class Scholar {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/gadgetbadgetdb?useTimezone=true&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return con;
	}

	public String readresearchstatus() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border='1px solid black' width='600' cellspacing='0' cellpadding='0' border-spacing='0' style=\"margin:0;padding:0;\"><tr><th>ResearchID</th>" + "<th>Progress</th>"
					+ "<th>Comment</th>" + "<th>Approval</th>"+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from researchstatus";
			Statement stmt = con.createStatement(); // query inserted to a statement and executed
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String refID = Integer.toString(rs.getInt("refID"));
				String researchID = Integer.toString(rs.getInt("researchID"));
				String progress = Integer.toString(rs.getInt("progress"));
				String comment = rs.getString("comment");
				String approval = rs.getString("approval");
				
				// Add into the html table
				output += "<tr><td>" +  researchID +"</td>";
				output += "<td>" + progress + "</td>";
				output += "<td>" + comment + "</td>";
				output += "<td>" + approval + "</td>";
			/*	output += "<td><form method='post' action='researchstatus.jsp'>"
						+ "<input name='sid' type='hidden' value='" + refID + "'>" + "</form></td>";*/
				
				//button
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-itemid='" + refID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + refID + "'></td></tr>";
				
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading ";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertresearchstatus(String researchID, String progress, String comment, String approval) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into researchstatus (`refID`,`researchID`,`progress`,`comment`,`approval`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(researchID));
			preparedStmt.setInt(3, Integer.parseInt(progress));
			preparedStmt.setString(4, comment);
			preparedStmt.setString(5, approval);
			// execute the statement
			preparedStmt.execute();
			
			
			con.close();
			String newResearchStatus = readresearchstatus(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newResearchStatus + "\"}"; 
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":  \"Error while inserting the status.\"}"; 
					 System.err.println(e.getMessage()); 
		}
		return output;
	}

	public String updatesresearchstatus(String refID, String researchID, String progress, String comment,
			String approval) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE researchstatus SET researchID=?,progress=?,comment=?,approval=? WHERE refID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(researchID));
			preparedStmt.setInt(2, Integer.parseInt(progress));
			preparedStmt.setString(3, comment);
			preparedStmt.setString(4, approval);
			preparedStmt.setInt(5, Integer.parseInt(refID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newResearchStatus = readresearchstatus(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newResearchStatus + "\"}"; 
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":  \"Error while updating the status.\"}"; 
					 System.err.println(e.getMessage()); 
		}
		return output;
	}

	public String deleteresearchstatus(String refID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from researchstatus where refID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(refID));
			System.out.println(refID);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newResearchStatus = readresearchstatus(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newResearchStatus + "\"}"; 
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":  \"Error while deleting the status.\"}"; 
					 System.err.println(e.getMessage()); 
		}
		return output;
	}

}
