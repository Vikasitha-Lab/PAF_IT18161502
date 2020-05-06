<%@page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Management</title>
    <link rel="stylesheet" href="Views/css/bootstrap.min.css">
    <script src="Components/jquery-3.5.0.min.js"></script>
    <script src="Components/usersjs.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-6">
            <h1>User Management For HealthCare System</h1>
            
            <form id="formUser" name="formUser" method="post" action="users.jsp">
                User Name:
                <input id="name" name="name" type="text"
                       class="form-control form-control-sm">
                <br> 
                ID Number:
                <input id="idno" name="idno" type="text"
                       class="form-control form-control-sm">
                <br> 
                Address:
                <input id="address" name="address" type="text"
                       class="form-control form-control-sm">
                <br> 
                Date of Birth:
                <input id="dob" name="dob" type="text"
                       class="form-control form-control-sm">
                <br>
                Age:
                <input id="age" name="age" type="text"
                       class="form-control form-control-sm">
                <br>
                Gender:
                <input id="sex" name="sex" type="text"
                       class="form-control form-control-sm">
                <br>
                Phone Number:
                <input id="phone" name="phone" type="text"
                       class="form-control form-control-sm">
                <br>
                Email Address:
                <input id="email" name="email" type="text"
                       class="form-control form-control-sm">
                <br>
                Password:
                <input id="password" name="password" type="text"
                       class="form-control form-control-sm">
                <br>
                <input id="btnSave" name="btnSave" type="button" value="Save"
                       class="btn btn-primary">
                <input type="hidden" id="hiduseridSave" name="hiduseridSave" value="">
            </form>
            <div id="alertSuccess" class="alert alert-success"></div>
            <div id="alertError" class="alert alert-danger"></div>
            <br>
            <div id="divUsersGrid">
                <%
                    User userObj = new User();
                    out.print(userObj.readUsers());
                %>
            </div>

        </div>
    </div>
</div>

</body>
</html>
