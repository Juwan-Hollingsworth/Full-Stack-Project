/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controls;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import models.SHUsers;
import util.UserStatus;
import models.SHDevices;
import java.util.Enumeration;

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
@WebServlet(name = "ListDevicesCTL", urlPatterns = {"/ListDevicesCTL"})
public class ListDevicesCTL extends HttpServlet {

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
            out.println("<title>Servlet ListDevicesCTL</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListDevicesCTL at " + request.getContextPath() + "</h1>");
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
        
         String dispatchPage;
         String relationalOperator;
         String objectIdToPass="-1";    
         
        if (!UserStatus.getModificationObjectId().equals("-1")){
            dispatchPage = "ObjectModification.jsp";
            relationalOperator = "=";
            objectIdToPass = UserStatus.getModificationObjectId();
            UserStatus.setModificationObjectId("-1");
            UserStatus.setTemporaryVariable(objectIdToPass);
        }  
        else {
            dispatchPage = "ListDevices.jsp";
            relationalOperator = "<>";
        }         
        
        //processRequest(request, response);
        //define membership variable
        SHDevices myDevice = new SHDevices();
        //pull all memberhship data
        List<SHDevices> deviceList = myDevice.getDevices(objectIdToPass);
        request.setAttribute("devices", deviceList);
        
        RequestDispatcher rd
                = request.getRequestDispatcher(dispatchPage);
        rd.forward(request, response);
        System.out.println(dispatchPage);
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
        processRequest(request, response);
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



/* non admin data modify

        else if (UserStatus.getUserAccessLevel().equals("1")){
            dispatchPage = "AdminModification.jsp";
            relationalOperator = "=";
            userIdToPass = UserStatus.getCurrentUserId();
            UserStatus.setTemporaryVariable(userIdToPass);
        }    

*/