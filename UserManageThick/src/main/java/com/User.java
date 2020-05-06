package com;

import java.sql.*;

public class User {
	private Connection connect() {

        Connection con = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userman", "root", "");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return con;

    }

    public String readUsers() {

        String output = "";

        try {

            Connection con = connect();

            if (con == null) {

                return "Error while connecting to the database for reading. ";

            }

            // Prepare the html table to be displayed
            output = "<table class='table'>"+ "<tr> <th>User_Name</th ><th >ID_Number</th ><th>Address</th> <th>Birth_Date</th><th>Age</th><th>Sex</th><th>Phone_Number</th><th>E-mail_Address</th><th>Password</th> " + " <th> Update </th><th> Remove </th></tr> ";
            
            String query = "select * from users";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // iterate through the rows in the result set
            while (rs.next()) {

                String userid = Integer.toString(rs.getInt("userid"));
                String name = rs.getString("name");
                String idno = rs.getString("idno");
                String address = rs.getString("address");
                String dob = rs.getString("dob");
                String age = rs.getString("age");
                String sex = rs.getString("sex");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String password = rs.getString("password");

                // Add into the html table
                output += "<tr><td><input id='hiduseridUpdate' name = 'hiduseridUpdate' type = 'hidden' value = '" + userid + "'>" + name + "</td>";
                output += "<td>" + idno + "</td>";
                output += "<td>" + address + "</td>";
                output += "<td>" + dob + "</td>";
                output += "<td>" + age + "</td>";
                output += "<td>" + sex + "</td>";
                output += "<td>" + phone + "</td>";
                output += "<td>" + email + "</td>";
                output += "<td>" + password + "</td>";

                // buttons
                output += "<td><input name='btnUpdate' type = 'button' value = 'Update' class='btnUpdate btn btn-secondary' ></td > " + "<td><input name='btnRemove' type = 'button' value = 'Remove' class='btnRemove btn btn-danger' data-userid = '" + userid + "'>" + "</td></tr>";

            }

            con.close();

            // Complete the html table
            output += "</table>";

        } catch (Exception e) {

            output = "Error while reading the users.";
            System.err.println(e.getMessage());

        }

        return output;

    }

    public String insertUser(String name, String idno, String address, String dob, String age,
			String sex, String phone, String email, String password) {
		String output = "";

        try {

            Connection con = connect();

            if (con == null) {

                return "Error while connecting to the database for inserting.";

            }

            // create a prepared statement
            String query = " insert into users (userid,name,idno,address,dob,age,sex,phone,email,password)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            // binding values
            preparedStmt.setInt(1, 0);
            preparedStmt.setString(2, name);
            preparedStmt.setString(3, idno);
            preparedStmt.setString(4, address);
            preparedStmt.setString(5, dob);
            preparedStmt.setString(6, age);
            preparedStmt.setString(7, sex);
            preparedStmt.setString(8, phone);
            preparedStmt.setString(9, email);
            preparedStmt.setString(10, password);

            // execute the statement
            preparedStmt.execute();
            con.close();
            String newUsers = readUsers();
            output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";

        } catch (Exception e) {

            output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}";
            System.err.println(e.getMessage());

        }

        return output;
	}

    public String updateUser(String hiduseridSave, String name, String idno, String address, String dob, String age,
			String sex, String phone, String email, String password) {
		String output = "";

        try {

            Connection con = connect();

            if (con == null) {

                return "Error while connecting to the database for updating. ";

            }

            // create a prepared statement
            String query = "UPDATE users SET name =?,idno =?,address =?,dob =?,age =?,sex =?,phone =?,email =?,password =? WHERE userid =?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            // binding values
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, idno);
            preparedStmt.setString(3, address);
            preparedStmt.setString(4, dob);
            preparedStmt.setString(5, age);
            preparedStmt.setString(6, sex);
            preparedStmt.setString(7, phone);
            preparedStmt.setString(8, email);
            preparedStmt.setString(9, password);
            preparedStmt.setInt(10, Integer.parseInt(hiduseridSave));

            // execute the statement
            preparedStmt.execute();
            con.close();
            String newUsers = readUsers();
            output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";

        } catch (Exception e) {

            output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
            System.err.println(e.getMessage());

        }

        return output;

    }

    public String deleteUser(String userid) {

        String output = "";

        try {

            Connection con = connect();

            if (con == null) {

                return "Error while connecting to the database for deleting. ";

            }

            // create a prepared statement
            String query = "delete from users where userid=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            // binding values
            preparedStmt.setInt(1, Integer.parseInt(userid));

            // execute the statement
            preparedStmt.execute();
            con.close();
            String newUsers = readUsers();
            output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";

        } catch (Exception e) {

            output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
            System.err.println(e.getMessage());

        }

        return output;

    }

}
