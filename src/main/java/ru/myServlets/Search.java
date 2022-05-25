package ru.myServlets;

import ru.retail.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@WebServlet(urlPatterns = "/search")
public class Search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String conditionText = "";
        String productName = request.getParameter("productName");
        String productCategory = request.getParameter("productCategory");
        String productCountry = request.getParameter("productCountry");
        float priceFrom = Float.valueOf(request.getParameter("priceFrom"));
        float priceUp = Float.valueOf(request.getParameter("priceUp"));
        boolean discounted = Boolean.valueOf(request.getParameter("discounted"));
        conditionText = addConditionChar(conditionText, "productName", productName);
        conditionText = addConditionChar(conditionText, "productCategory", productCategory);
        conditionText = addConditionChar(conditionText, "productCountry", productCountry);

        LinkedHashMap<Integer, String> foundProducts = ProductService.findProducts(conditionText);
        request.setAttribute("foundProducts", foundProducts);
        int basketSize = 0;
        ArrayList<Integer> basket = (ArrayList<Integer>) request.getSession().getAttribute("basket");
        if (basket != null) {
            basketSize = basket.size();
        }
        request.setAttribute("basketSize", basketSize);
        String path = "/search.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    private String addConditionChar(String conditionText, String columnName, String value) {
        if (value != null) {
            if (!value.isEmpty()) {
                if (!conditionText.isEmpty()) {
                    conditionText = conditionText + " and ";
                }
                conditionText = conditionText + " " + columnName + " like '%" + value + "%'";
            }
        }
        return conditionText;
    }
}
