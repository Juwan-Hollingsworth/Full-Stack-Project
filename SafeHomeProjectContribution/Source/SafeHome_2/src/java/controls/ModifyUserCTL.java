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
@WebServlet(name = "ModifyUserCTL", urlPatterns = {"/ModifyUserCTL"})
public class ModifyUserCTL extends HttpServlet {

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
        
        if (paramName.startsWith("ModifyDeleteUser_"))
        {
            SHUsers tempUser = new SHUsers();
            String userId=paramName.substring(paramName.indexOf('_')+1);
            String operationType = request.getParameter(paramName);
             // Get the parameter names

            if (operationType.equals("Delete")){
               validate = tempUser.DeleteUser(userId);
            }
            else if (operationType.equals("ModifyUser")){
               UserStatus.setModificationUserId(userId);
               redirectToAdminModify = true;
            }
        }
        
        if (paramName.equals("UserPasswordChange"))
        {
            // Get the parameter names
            String password = request.getParameter("UserPasswordChange");
            SHUsers tempUser = new SHUsers();
            validate = tempUser.ChangePassword(password);
        }
        
        if ((paramName.equals("UserNameNew") || (paramName.equals("modifyAllUserName"))))
        {
            // Get the parameter names
            String userName = request.getParameter(paramName);
            String password = request.getParameter("pwd");
            String accessLevel = request.getParameter("AccessLevel");
            SHUsers tempUser = new SHUsers();
            
            if (paramName.equals("UserNameNew"))
            {
                String userId = tempUser.generateId();
                validate = tempUser.CreateUser(userId, userName, password, accessLevel);
            }
            else
            {
                validate = tempUser.ModifyUser(userName, password, accessLevel,UserStatus.getTemporaryVariable());
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
        else if ((redirectToAdminModify == true) && (validate == false) && (UserStatus.getUserAccessLevel().equals("0")))  {
            response.sendRedirect("ListMembersCTL");
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
