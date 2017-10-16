<%-- 
    Document   : shoppingList
    Created on : Oct 11, 2017, 1:40:18 PM
    Author     : 643699
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <br>
        Hello, ${username} <a href ="ShoppingList?action=loggedout">Logout</a>
        <br>
        <h1>List</h1>
        <br>
        <form action="ShoppingList?action=add" method ="post">
            Add Item: <input type ="text" name="newItem">
            <input type ="submit" value="Add Item">
        </form>
        <br>
        <form action ="ShoppingList?action=delete" method ="post">
            
            <c:forEach var="item" items="${sessionScope.itemList}">
                <input type ="radio" name="items" value="${item}"><label><c:out value="${item}"/></label><br>                 
            </c:forEach>
            <c:if test="${not empty sessionScope.itemList}">
                <input type="submit" value="delete">
            </c:if>
        </form>
    </body>
</html>
