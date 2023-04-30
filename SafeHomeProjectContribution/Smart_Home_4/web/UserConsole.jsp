<%-- 
    Document   : AdminConsole
    Created on : Apr 17, 2023, 1:28:48 AM
    Author     : Dregs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
  <head>
    <title>Smart Home Menu</title>
    <style>
      body {
        background-color: #f1f1f1;
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
      }
      .menu-container {
        position: relative;
        width: 400px;
        margin: 80px auto 0;
        padding: 40px;
        background-color: #ffffff;
        box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
      }
      .menu-container:before,
      .menu-container:after {
        content: '';
        position: absolute;
        top: 0;
        bottom: 0;
        width: 70%; /* Increase the width */
        background-color: #0077b6;
        z-index: -1;
      }
      .menu-container:before {
        left: -35%; /* Adjust the position */
        transform: skewX(-15deg);
      }
      .menu-container:after {
        right: -35%; /* Adjust the position */
        transform: skewX(15deg);
      }
      h1 {
        text-align: center;
        font-size: 32px;
        font-weight: bold;
        color: #0077b6;
        margin-top: 0;
      }
      ul {
        list-style-type: none;
        padding: 0;
        margin: 0;
      }
      li {
        margin-bottom: 16px;
      }
      a {
        display: block;
        padding: 5px;
        background-color: #0077b6;
        color: #ffffff;
        text-align: center;
        text-decoration: none;
        border-radius: 4px;
        font-size: 14px;
        font-weight: bold;
        box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
      }
      a:hover {
        background-color: #005d8f;
      }
      .bold-blue {
       font-weight: bold;
       color: green;
       }

    </style>
  </head>
  <body>
    <div class="menu-container">
      <ul>
        <h1>User Console</h1>
        <span class="bold-blue">Welcome home, ${username}!</span><br><br>
         <a href="ChangePassword.jsp">Change User Password</a><br>
         <a href="AddNewObject.jsp">Add New Device</a><br>
         <a href="ListDevicesCTL">Manage Devices</a><br>
         <a href="index.html">Logout</a>
         <br><br>
         <p><strong><u>${ActionResult}</u></strong></p>
      </ul>
    </div>
  </body>
</html>
