package ru.myServlets;

import ru.retail.Product;
import ru.retail.Storage;

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

@WebServlet(urlPatterns = "/putInBasket")
public class PutInBasket extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        if (productId != null) {
            Storage storage = Storage.getInstance();
            Product product = null;
            for (Product p: storage.getSetOfProducts()) {
                if (p.getId().equals(productId)) {
                    product = p;
                    break;
                }
            }
            if (product != null) {
                HttpSession session = request.getSession();
                ArrayList<Product> basket = (ArrayList<Product>) session.getAttribute("basket");
                if (basket == null) {
                    basket = new ArrayList<Product>();
                }
                basket.add(product);
                session.setAttribute("basket", basket);
            }
        }
        String path = "/";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
