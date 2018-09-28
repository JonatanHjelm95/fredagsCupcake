<%-- 
    Document   : products
    Created on : Sep 27, 2018, 12:02:35 PM
    Author     : jonab
--%>

<%@page import="data.Topping"%>
<%@page import="data.Bottom"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet"
              type = "text/css"
              href = "indexStyle.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <title>Products</title>
    </head>
    <body>
        <div class="topnav">
            <!--<a id="login" href="?origin=signup">sign up</a>
            <a id="login" href="?origin=login">Login</a> -->
            <%=request.getAttribute("html")%>

            <h1 id="header" >Products</h1>

            <a id="home"  href="?origin=index">Home</a>
            <a id="products" class="active" href=#?origin=products">Products</a>
        </div>
        <form action="Control" method="POST" id="extras">
            <%=request.getAttribute("bottoms")%>
            <%=request.getAttribute("toppings")%>

            <input type="hidden" name="origin" value="addtocart"/>

            <input type="submit" value="Add to Shopping Cart"/>

        </form>

    </body>
</html>
