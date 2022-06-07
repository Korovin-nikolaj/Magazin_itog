package ru.myServlets.privateZone;

import ru.retail.service.MoneyService;
import ru.retail.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/private/account")
public class AccountPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/private/account.jsp";
        Object clientId = req.getSession().getAttribute("clientId");
        if (clientId != null) {
            float clientBalance = MoneyService.getClientBalance((Integer)clientId);
            req.setAttribute("clientBalance", clientBalance);
        } else {
            req.setAttribute("returnPage", "userLogin.jsp");
            path = "errorPage.jsp";
        }
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }
}
