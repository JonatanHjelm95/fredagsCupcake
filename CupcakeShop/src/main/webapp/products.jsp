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
              href = "DefaultStyle.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <title>Products</title>
    </head>
    <body>
        <div class="topnav">
            <a id="login" href="?origin=signup">sign up</a>
            <a id="login" href="?origin=login">Login</a>
            <h1 id="header" >Products</h1>

            <a id="home"  href="?origin=index">Home</a>
            <a id="products" class="active" href=#?origin=products">Products</a>
        </div>
        <form action="Control" method="POST" id="extras">
            <select name="bottom" form="extras">
                <%
                    ArrayList<Bottom> bottoms = (ArrayList) request.getAttribute("bottoms");
                    for (int i = 0; i < bottoms.size(); i++) {
                        out.println("<option value=\"" + bottoms.get(i).getName() + "\">" + bottoms.get(i).getName() + ", " + bottoms.get(i).getPrice() + " kr" + "</option>");
                    }%>
            </select>
            <select name="topping" form="extras">
                <%
                    ArrayList<Topping> toppings = (ArrayList) request.getAttribute("toppings");
                    for (int i = 0; i < toppings.size(); i++) {
                        out.println("<option value=\"" + toppings.get(i).getName() + "\">" + toppings.get(i).getName() + ", " + toppings.get(i).getPrice() + " kr" + "</option>");
                    }%>
            </select>
            <input type="hidden" name="origin" value="addtocart"/>

            <input type="submit" value="Add to Shopping Cart"/>

        </form>

    </body>
</html>
