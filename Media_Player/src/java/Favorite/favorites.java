/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Favorite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.println;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DARSHAN
 */
public class favorites extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDB", "root", "root");
            Statement stmt = con.createStatement();
            String file = request.getParameter("file");
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("user");
            if (file != null) {
                ResultSet rs=stmt.executeQuery("select * from root.favorites where filename='"+file+"' and username='"+name+"'");
                if(!rs.next()){
                    stmt.execute("insert into root.favorites (filename,username) values('" + file + "','" + name + "')");
                }
                response.sendRedirect("http://localhost:8080/Media_Player/audio");
            }
            String rfile=request.getParameter("rfile");
            if(rfile!=null){
                stmt.execute("delete from root.favorites where filename='"+rfile+"' and username='"+name+"'");
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>favorites</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='home.html'>");
            out.println("<input type='submit' value='<-back'></input></form>");
            out.println("<h1>Favorites Files</h1>");
            ResultSet rs=stmt.executeQuery("select filename from root.favorites where username='"+name+"'");
            while(rs.next())
            {
                out.println("<form action='#'>");
                out.println("<input type='hidden' name='rfile' value='"+rs.getString(1)+"'></input>");
                out.println("<h4>"+rs.getString(1)+"</h4>");
                out.println("<input type='submit' value='remove'></input><hr></form>");
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(favorites.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(favorites.class.getName()).log(Level.SEVERE, null, ex);
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
