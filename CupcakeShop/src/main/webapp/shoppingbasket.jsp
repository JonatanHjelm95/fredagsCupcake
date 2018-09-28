<%-- 
    Document   : shoppingbasket
    Created on : Sep 27, 2018, 11:47:37 PM
    Author     : Fen
--%>

<%@page import="data.LineItem"%>
<%@page import="data.ShoppingBasket"%>
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

        <title>Shoppingcart</title>
    </head>
    <body>
        <div class="topnav">
            <%=request.getAttribute("html")%>

            <h1 id="header" >Shopping Cart</h1>

            <a id="home"  href="?origin=index">Home</a>
            <a id="products" href="?origin=products">Products</a>
        </div>
        <h1>Shopping Cart</h1>
        <div>
            <form action="Control" method="POST" id="checkout">
                <%=request.getAttribute("cartContent") // ShoppingBasket sb = (ShoppingBasket) request.getSession().getAttribute("shoppingbasket");
                        // for (LineItem item : sb.getBasket()) {
                        //     out.println("<li>" + item + "</li>");
                        // }
                        %>

                <input type="hidden" name="origin" value="checkout"/>
                <input type="submit" value="checkout">
            </form>
            <form action="Control" method="POST" id="products">
                <input type="submit" formaction="Control" value="continue shopping">
                <input type="hidden" name="origin" value="products"/>
            </form>

        </div>
    </body>
</html>
