<%-- 
    Document   : index
    Created on : Sep 27, 2018, 10:43:07 AM
    Author     : jonab
--%>

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
        <title>Index</title>
    </head>
    <body>
        
        <div class="topnav">
            <!--<a id="login" href="?origin=signup">sign up</a>
            <a id="login" href="?origin=login">Login</a> -->
            <%=request.getAttribute("html")%>
            <h1 id="header" >Cupcake Shop</h1>
            <a id="home" class="active" href="?origin=index">Home</a>
            <a id="products" href="?origin=products">Products</a>
        </div>
    </body>
</html>
