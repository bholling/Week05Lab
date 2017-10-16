<%-- 
    Document   : register
    Created on : Oct 11, 2017, 1:40:00 PM
    Author     : 643699
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List Registration</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <br>
        <form action="ShoppingList?action=register" method="post">
            Username: <input type = "text" name ="newUsername"><br>
            <input type ="submit" value="Register">
        </form>
        ${error}
    </body>
</html>
