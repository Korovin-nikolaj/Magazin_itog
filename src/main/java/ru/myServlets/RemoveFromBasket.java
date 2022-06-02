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
import java.util.LinkedHashMap;

@WebServlet(urlPatterns = "/removeFromBasket")
public class RemoveFromBasket extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        if (productId != null) {
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            LinkedHashMap<Integer, String> basket = (LinkedHashMap<Integer, String>) session.getAttribute("basket");
            if (basket == null) {
                basket = new LinkedHashMap<>();
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
