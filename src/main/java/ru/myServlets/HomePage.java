package ru.myServlets;

import ru.retail.Storage;
import ru.retail.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@WebServlet(urlPatterns = "/")
public class HomePage extends HttpServlet {
    public static Storage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        storage = Storage.getInstance();
    }

    @Override
    public void destroy() {
        super.destroy();
        Connection conn = Storage.getInstance().getConn();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ArrayList<Integer> basket = (ArrayList<Integer>) session.getAttribute("basket");
        if (basket == null) {
            basket = new ArrayList<>();
        }
        LinkedHashMap<Integer, String> allProducts = ProductService.getAllProducts();
        request.setAttribute("basketSize", basket.size());
        request.setAttribute("allProducts", allProducts);
        String path = "/homePage.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
