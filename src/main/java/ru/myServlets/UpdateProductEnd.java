package ru.myServlets;

import ru.retail.Product;
import ru.retail.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

@WebServlet(urlPatterns = "/updateProductEnd")
public class UpdateProductEnd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        String productName = req.getParameter("productName");
        String price = req.getParameter("price");
        String productCategory = req.getParameter("productCategory");
        String productCountry = req.getParameter("productCountry");
        String discounted = req.getParameter("discounted");
        Product product = new Product(productName, Integer.valueOf(productId), Float.valueOf(price), productCategory, productCountry, Boolean.valueOf(discounted));
        int countRows = ProductService.updateProduct(product);
        req.setAttribute("countUpdateRows", countRows);
        req.setAttribute("productName", productName);
        LinkedHashMap<Integer, String> allProducts = ProductService.getAllProducts();
        req.setAttribute("allProducts", allProducts);
        String path = "/editProducts.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
