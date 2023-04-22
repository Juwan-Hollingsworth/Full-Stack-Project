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
        margin: 20px auto 0;
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
    </style>
  </head>
  <body>
    <div class="menu-container">
        <h1>New Object Data</h1>
        <form action="ModifyDevicesCTL" method="post">
            <div id ="DeviceNameNew">Device Name</div>
            <input type="text" name="DeviceNameNew"/> </br>
            <div id ="IPAddress">IP Address</div>
            <input type="text" name="IPAddress"/> </br>
            <div id ="DeviceLocation">Device Location</div>
            <input type="text" name="DeviceLocation"/> </br>
            <div id ="IsShared">Shared Device</div>
            <input type="text" name="IsShared"/> </br>            
            <input type="submit" value="Add Device" id="submit">
        </form>
      </ul>
    </div>
  </body>
</html>
