<%-- 
    Document   : errorPage
    Created on : Sep 27, 2018, 12:56:03 PM
    Author     : jonab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="topnav">
            <a id="login" href="?origin=signup">sign up</a>
            <a id="login" href="?origin=login">Login</a>
            <h1 id="header" >Cupcake Shop</h1>

            <a id="home" class="active" href="?origin=index">Home</a>
            <a id="products" href="?origin=products">Products</a>
        </div>
        <h1> <%=request.getAttribute("error") %></h1>
    </body>
</html>
