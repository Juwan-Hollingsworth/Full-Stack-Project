/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controls;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.io.PrintWriter;
import models.SHUsers;
import util.UserStatus;
import java.util.Enumeration;
import models.SHDevices;

/*
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/

/**
 *
 * @author jqu
 */
@WebServlet(name = "ModifyDevicesCTL", urlPatterns = {"/ModifyDevicesCTL"})
public class ModifyDevicesCTL extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginCTL</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginCTL at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         //processRequest(request, response);
        Enumeration<String> paramNames = request.getParameterNames();
        String paramName = paramNames.nextElement();
        boolean validate = false;
        boolean redirectToAdminModify = false;
            
        if (paramName.startsWith("ModifyDeleteDevice_"))
        {
            SHDevices tempDevice = new SHDevices();
            String objectID=paramName.substring(paramName.indexOf('_')+1);
            String operationType = request.getParameter(paramName);
             // Get the parameter names

            if (operationType.equals("Delete")){
               validate = tempDevice.DeleteDevice(objectID);
            }
            else if (operationType.equals("ModifyDevice")){
               UserStatus.setModificationObjectId(objectID);
               redirectToAdminModify = true;
            }
        }
        
        
        if ((paramName.equals("DeviceNameNew") || (paramName.equals("modifyAllDeviceName"))))
        {
            // Get the parameter names
            String deviceName = request.getParameter(paramName);
            String ipAddress = request.getParameter("IPAddress");
           System.out.println("jfv  "+ipAddress);
            String DeviceLocation = request.getParameter("DeviceLocation");
            String IsShared = request.getParameter("IsShared");
            SHDevices tempDevice = new SHDevices();
            
            if (paramName.equals("DeviceNameNew"))
            {
                String[] ObjectAndOwnershipIds = tempDevice.generateId();
                validate = tempDevice.CreateDevice(ObjectAndOwnershipIds[1],deviceName,ipAddress,DeviceLocation,IsShared,UserStatus.getCurrentUserId(),ObjectAndOwnershipIds[1]);
            }
            else
            {
                validate = tempDevice.ModifyDevice(deviceName, ipAddress, DeviceLocation,IsShared,UserStatus.getTemporaryVariable());
                UserStatus.setTemporaryVariable("-1");
            }
         }    
        
        String activeConsole = "";
        if (UserStatus.getUserAccessLevel().equals("0"))
            activeConsole = "AdminConsole.jsp";
        else if (UserStatus.getUserAccessLevel().equals("1"))
            activeConsole = "UserConsole.jsp";
        String actionResult;
        RequestDispatcher resultDispatcher
                = request.getRequestDispatcher(activeConsole);
        
        if((validate == true) && (UserStatus.getUserAccessLevel().equals("0"))) {
            actionResult = "Task Completed Successfully.";
            request.setAttribute("ActionResult", actionResult);
            resultDispatcher.forward(request,response);
        }
        else if ((validate == true) && (!UserStatus.getUserAccessLevel().equals("0"))) {
            actionResult = "Task Completed Successfully.";
            request.setAttribute("ActionResult", actionResult);
            resultDispatcher.forward(request,response);
        }
        else if ((redirectToAdminModify == true) && (validate == false))  {
            response.sendRedirect("ListDevicesCTL");
        }
        else{
            actionResult = "Task Failed.";
            request.setAttribute("ActionResult", actionResult);
            resultDispatcher.forward(request,response);
        }
        
        
    }
        
          /**
         * Returns a short description of the servlet.
        *
        * @return a String containing servlet description
        */
        @Override
        public String getServletInfo() {
            return "Short description";
        }// </editor-fold>  

    }
