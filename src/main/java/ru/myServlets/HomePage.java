package ru.myServlets;

import ru.retail.Product;
import ru.retail.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(urlPatterns = "/")
public class HomePage extends HttpServlet {
    public static Storage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        storage = Storage.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            ArrayList<Product> basket = (ArrayList<Product>) session.getAttribute("basket");
            if (basket == null) {
                basket = new ArrayList<Product>();
            }
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Интернет-магазин</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p><a href=\"http://localhost:8082/account\"> Профиль </a> | ");
            out.println("<a href=\"http://localhost:8082/basket.jsp\"> Корзина(" + basket.size() + ") </a></p>");
            out.println("<h3>Поиск товаров:</h3>");
            out.println("<form action=\"/search\" method=\"post\">");
            out.println("Наименование: <input name = \"productName\"/>  |  ");
            out.println("Категория: <input name = \"productCategory\"/> <br><br>");
            out.println("Цена от: <input name = \"priceFrom\" type = \"number\"/>  |  ");
            out.println("Цена до: <input name = \"priceUp\" type = \"number\"/> <br><br>");
            out.println("Страна производства: <input name = \"productCountry\"/> |  ");
            out.println("Только со скидкой: <input name = \"discounted\" type=\"checkbox\"/> <br><br>");
            out.println("<input type=\"submit\" value = \"Найти\"/>");
            out.println("</form>");
            out.println("<h3>Каталог товаров: </h3>");
            for (Map.Entry<String, Product> entry : storage.getMapOfProducts().entrySet()) {
                out.println("<p>Положить в корзину <a href=\"http://localhost:8082/putInBasket?productId="  + entry.getKey() + "\">" + entry.getValue().getName() + "</a> по цене " + entry.getValue().getPrice() + "</p>");
            }
            out.println("</body>");
            out.println("</html>");

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }
}
