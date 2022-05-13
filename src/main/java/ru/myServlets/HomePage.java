package ru.myServlets;

import ru.retail.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Интернет-магазин</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p><a href=\"http://localhost:8080/account\"> Профиль </a>");
            out.println("<h3>Каталог товаров: </h3>");
//            for (ru.retail.Product product: storage.getSetOfProducts()){
//     out.println("<p>Купить <a href=\"http://localhost:8080/myRetail/putInBasket?basketString=" + basketStringForLink + product.getId() + "\">" + product.getName() + "</a> по цене " + product.getPrice() + "</p>");
// }
            out.println("<h3>Ваша корзина: </h3>");
//            if (!basketString.isEmpty()) {
//                String[] arrayOfProduct = basketString.split(",");
//                for (String s : arrayOfProduct) {
//                    out.println("<p>Удалить <a href=\"http://localhost:8080/myRetail/putInBasket?basketString=" + basketString + "&removableProduct=" + s + "\">" + s + "</a></p>");
//                }
//                out.println("<h3>Оплата</h3>");
//                out.println("<p><a href=\"http://localhost:8080/myRetail/bay?basketString=" + basketString + "\">Оплатить</a></p>");
//            }
            out.println("</body>");
            out.println("</html>");

        }
    }
}
