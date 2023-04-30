<%-- 
    Document   : AdminModification
    Created on : Apr 11, 2023, 3:12:20 PM
    Author     : jqu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html>
  <head>
    <title>Smart Device List</title>
    <style>
      body {
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
      }
      .container {
        position: relative;
        max-width: 1200px;
        margin: 80px auto 0;
        padding: 0px;
        background-color: #fff;
        box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
      }
      .container:before,
      .container:after {
        content: '';
        position: absolute;
        top: 0;
        bottom: 0;
        width: 0%;
        background-color: #0077b6;
        z-index: -1;
      }
      .container:before {
        left: -30%;
        transform: skewX(-15deg);
      }
      .container:after {
        right: 10%;
        transform: skewX(15deg);
      }
      h1 {
        text-align: center;
        font-size: 32px;
        font-weight: bold;
        color: #0077b6;
        margin-top: 0;
      }
      table {
        width: 100%;
        border-collapse: collapse;
        border: 3px solid #0077b6;
        margin-bottom: 16px;
        table-layout: fixed;
      }
      th {
        background-color: #0077b6;
        color: #fff;
        font-size: 16px;
        font-weight: bold;
        padding: 10px;
        text-align: left;
      }
      td {
        padding: 10px;
        border: 1px solid #ccc;
        font-size: 16px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
      input[type="text"] {
        display: block;
        width: 100%;
        padding: 10px;
        font-size: 16px;
        border-radius: 4px;
        border: 1px solid #ccc;
        box-sizing: border-box;
        margin-bottom: 16px;
      }
      input[type="submit"] {
        background-color: #0077b6;
        color: #fff;
        border: none;
        padding: 10px 16px;
        font-size: 16px;
        border-radius: 4px;
        cursor: pointer;
        box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
      }
      input[type="submit"]:hover {
        background-color: #005d8f;
      }
	  select[name^=ModifyDeleteUser_] {
  display: block;
  width: 50%;
  padding: 10px;
  font-size: 16px;
  border-radius: 4px;
  border: 1px solid #ccc;
  box-sizing: border-box;
  margin-bottom: 16px;
}

button[type="submit"] {
  background-color: #0077b6;
  color: #fff;
  border: none;
  padding: 5px 8px;
  font-size: 16px;
  border-radius: 4px;
  cursor: pointer;
  box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
  margin-left: 10px;
}

button[type="submit"]:hover {
  background-color: #005d8f;
}
    </style>
  </head>
  <body>
    <div class="container">
        <h1>Device List</h1>
        <table border="3">
            <thead>
            <th>Object Id</th>
            <th>Device Name</th>
            <th>IP Address</th>
            <th>Location</th>
            <th>Shared<br>(Shared = 1 | Private = 0)</th>
            <th><form id="filterForm" action="ModifyDevicesCTL" method="post">
                <input type="text" name="filterData" value ="Filter" />
            </form></th>
            </thead>
            <tbody>
                <c:forEach items="${devices}" var="myDevice">
                    <tr>
                        <td>
                            <c:out value="${myDevice.getObjectID()}"></c:out>
                        </td>
                        <td>
                            <c:out value="${myDevice.getDeviceName()}"></c:out>
                        </td>
                        <td>
                            <c:out value="${myDevice.getIpAddress()}"></c:out>
                        </td>
                        <td>
                            <c:out value="${myDevice.getDeviceLocation()}"></c:out>
                        </td>
                        <td>
                            <c:out value="${myDevice.getIsShared()}"></c:out>
                        </td>                        
                        <td>
                            <form action="ModifyDevicesCTL" method="POST">
                            <select name="ModifyDeleteDevice_${myDevice.getObjectID()}">
                            <option value="ModifyDevice">Modify</option>
                            <option value="Delete">Delete</option>
                            <option value="ExtendOwnership">Extend</option>
                            <option value="ConnectDevice">Connect</option>
                            </select>
                            <button type="submit">Submit</button>
                            </form>
                        </td>       
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    </body>
    <script>
  // Add an event listener for when the form is submitted
  document.getElementById("filterForm").addEventListener("submit", function(e) {
    // Prevent the form from actually submitting
    e.preventDefault();
    
    // Get the input value
    var inputValue = document.getElementsByName("filterData")[0].value;
    
    // Create an AJAX request to submit the form data to the servlet
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "ModifyDevicesCTL");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("filterData=" + encodeURIComponent(inputValue));
    
    // Optionally, do something with the response from the server
    xhr.addEventListener("load", function() {
      console.log(xhr.responseText);
      location.reload();
    });
  });

</script>
</html>
