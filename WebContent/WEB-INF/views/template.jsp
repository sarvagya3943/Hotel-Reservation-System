<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
    <head>
        <meta http-equiv="Content-Type" content="text/html;" charset="ISO-8859-1">
        <title>Online hotel Booking</title>

        <!-- Include CSS files -->
        <link rel="stylesheet" href="static/css/bootstrap.min.css" />
        <link rel="stylesheet" href="static/css/angular-material.css" />
        <link rel="stylesheet" type="text/css" href="static/css/style.css" />
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/md-data-table/1.8.0/md-data-table-style.css" />

    </head>
    <body>
        <!-- render view dynamically -->
        <div ng-view>

        </div>

        <!-- Include JS files -->
        <!-- Include angular -->
         <script src="static/js/jquery.js"></script>
        <script src="static/js/bootstrap.min.js"></script>
        <script src="static/js/angular.js"></script>
        <script src="static/js/angular-route.js"></script>
        <script src="static/js/angular-cookies.js"></script>
        <script src="static/js/angular-animate.js"></script>
        <script src="static/js/angular-aria.js"></script>
        <script src="static/js/angular-messages.js"></script>
        <script src="static/js/angular-material.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/md-data-table/1.8.0/md-data-table-templates.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/md-data-table/1.8.0/md-data-table.min.js"></script>
        <!-- Include routing scripts -->
        <script src="static/js/app.js"></script>
        <!-- Include services -->
        <script src="static/app-services/searchData.service.js"></script>
        <script src="static/app-services/booking.service.js"></script>
        <script src="static/app-services/authentication.service.js"></script>
        <script src="static/app-services/hotel.service.js"></script>
        <script src="static/app-services/user.service.js"></script>


        <!-- Include angular controller scripts -->
        <script src="static/home/home.controller.js"></script>
        <script src="static/register/register.controller.js"></script>
        <script src="static/hotel/hotelDetails.controller.js"></script>
        <script src="static/login/login.controller.js"></script>
        <script src="static/booking/booking.controller.js"></script>
        <script src="static/user/profile.controller.js"></script>
        <script src  = "static/admin/admin.controller.js"></script>
         <script src  = "static/update/updateController.js"></script>
         <script src  = "static/viewBookings/viewController.js"></script>
    </body>
</html>
