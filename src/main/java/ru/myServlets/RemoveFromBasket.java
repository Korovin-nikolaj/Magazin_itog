package ru.myServlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/removeFromBasket")
public class RemoveFromBasket extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        if (productId != null) {
            HttpSession session = request.getSession();
            ArrayList<Integer> basket = (ArrayList<Integer>) session.getAttribute("basket");
            if (basket == null) {
                basket = new ArrayList<>();
            }
            basket.remove(Integer.valueOf(productId));
            session.setAttribute("basket", basket);
        }
        String path = "/basket.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
