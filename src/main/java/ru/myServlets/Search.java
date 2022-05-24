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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(urlPatterns = "/search")
public class Search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = (String)request.getParameter("productName");
        ArrayList<Product> foundProduct = new ArrayList<Product>();
        for (Map.Entry<Integer, Product> entry : Storage.getInstance().getMapOfProducts().entrySet()) {
            Product product = entry.getValue();
            if (product != null) {
                if (product.getName().contains(productName)) {
                    foundProduct.add(product);
                }
            }
        }
        request.setAttribute("foundProducts", foundProduct);
        int basketSize = 0;
        ArrayList<Product> basket = (ArrayList<Product>) request.getSession().getAttribute("basket");
        if (basket != null) {
            basketSize = basket.size();
        }
        request.setAttribute("basketSize", basketSize);
        String path = "/search.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
