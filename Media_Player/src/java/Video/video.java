/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Video;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DARSHAN
 */
public class video extends HttpServlet {

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
            out.println("<title>Video</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='home.html'>");
            out.println("<input type='submit' value='<-back'></input></form>");
            String path = System.getProperty("user.home").concat("\\Videos");
            File fp = new File(path);
            File list[] = fp.listFiles();
            int i;
            if (fp.exists()) {
                for (i = 0; i < list.length; i++) {
                    if (list[i].isFile()) {
                        String extension = new String("");
                        extension = list[i].getName().substring(list[i].getName().lastIndexOf('.'));
                        if (extension.equals(".mp4") || extension.equals(".mkv") || extension.equals(".avi") || extension.equals(".wmv") || extension.equals(".mpv")) {
                            out.println("<h4>" + list[i].getName() + "</h4><hr>");
                        }   
                    }
                }
            } else {
                System.out.println("file or directory does not exits");
            }
            out.println("<label>Select File to play :</label>");
            out.println("<input type='file'>");
            out.println("<video controls autoplay > </audio>"); 
            out.println("<script>");
            out.println("var input = document.querySelector('input[type=file]')");
            out.println("var video = document.querySelector('video')");
            out.println("input.onchange = function() {");
                out.println("video.src = URL.createObjectURL(input.files[0])");
            out.println("}</script>"); 
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
