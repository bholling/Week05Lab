/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serlvets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 643699
 */
public class ShoppingListServlet extends HttpServlet {


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
        String action = request.getParameter("action");
        if(action != null && action.equals("logout")){
            request.getSession().removeAttribute("user");
            request.getSession().removeAttribute("itemList");
            request.setAttribute("logout", "You have logged out. Thank you for visiting");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
        
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute("username");
        if(user != null){
            request.setAttribute("username", user);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }
        else{
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        if(action.equals("register")){
            String username = request.getParameter("newUsername");
            if(username == null || username.isEmpty()){
                request.setAttribute("error", "Invalid Username");
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("ShoppingList");
            return;
        }
        if(action.equals("add")){
            String item = request.getParameter("newItem");
            HttpSession session = request.getSession();
            ArrayList<String> list = (ArrayList<String>)session.getAttribute("itemList");
            if(list == null){
                list = new ArrayList<>();
            }
            list.add(item);
            session.setAttribute("itemList", list);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }
        
        if(action.equals("delete")){
            String items = request.getParameter("items");
            if(items != null){
                ArrayList<String> list = (ArrayList<String>)request.getSession().getAttribute("itemList");
                Iterator i = list.iterator();
                while(i.hasNext()){
                    String next = (String)i.next();
                    if(next.equals(items)){
                        i.remove();
                    }
                }
                request.getSession().setAttribute("itemList", list);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
            
            
        }
        
    }

}
