/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author emers
 */
public class ProductDetailsServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection dbcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?useSSL=false", "root", "1234");
           
            String pid= request.getParameter("pid"); 
   
            //images
            String carRowQuery = "Select* from carimages where pid = ?";
            
            PreparedStatement stmt = dbcon.prepareStatement(carRowQuery);
            stmt.setString(1, pid);
            
            ResultSet rs = stmt.executeQuery();
            String main_img = rs.getString("main_img");
            String sub_img = rs.getString("sub_img");
            String int_img = rs.getString("int_img");
            //end images
            
            //data
            String dataRowQuery = "Select * from cardata where pid=?";
            PreparedStatement dstmt = dbcon.prepareStatement(dataRowQuery);
            dstmt.setString(1, pid);
            
            ResultSet drs = stmt.executeQuery();
            String category = drs.getString("category");
            String make = drs.getString("make");
            String model = drs.getString("model");
            String trim = drs.getString("trim");
            String color = drs.getString("color");
            String year = drs.getString("year");
            String odo = drs.getString("odo");
            String gearbox = drs.getString("gearbox");
            String engine = drs.getString("engine");
            String price = drs.getString("price");
            String location = drs.getString("location");
            String description = drs.getString("description");
            

            String htmlText = 
            "<div class=\"images\">" +
                "<img src=" + main_img + "width=350 height=350>" +
                "<img src=" + sub_img + "width=350 height=350>" + 
                "<img src=" + int_img + "width=350 height=350>" +
            "</div>" +
            "<div class=\"description\">" +
                "<table id=\"description_table\">" + 
                    "<thead><tr><th colspan=2>Specifications</th></tr></thead>" +
                    "<tbody>" + 
                        "<tr><td>Category</td><td><" + category + "></td></tr>" +
                        "<tr><td>Make</td><td><"+ make + "></td></tr>" +
                        "<tr><td>Model</td><td><"+ model +"></td></tr>"+
                        "<tr><td>Trim</td><td><"+ trim + "></td></tr>" + 
                        "<tr><td>Color</td><td><"+ color + "></td></tr>" +
                        "<tr><td>Year</td><td><" + year + "></td></tr>" +
                        "<tr><td>Odo</td><td><" + odo + "></td></tr>" + 
                        "<tr><td>Gearbox</td><td><" + gearbox + "></td></tr>" +
                        "<tr><td>Engine</td><td><" + engine + "></td></tr>" + 
                        "<tr><td>Price</td><td><" + price + "></td></tr>" +
                        "<tr><td>Location</td><td><"+ location + "></td></tr>" +
                        "<tr><td>Description</td><td><" + description + "></td></tr>" +
                    "</tbody>" +
                "</table>"+
            "</div>" +
        "<div class=\"buttonDiv\">" +
                    //stripped from button until further notice
                    //onclick=\"orderNowOnClick()\"
            "<button type=\"button\" id=\"orderButton\" >"+
                "Order Now" +
            "</button>"+
        "</div>";
        
        out.write(htmlText);
    
        response.setStatus(200);
        stmt.close();
        rs.close();
        dbcon.close();
        
        }
        catch (Exception e){
            response.setStatus(500);
        }
        out.close();
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
