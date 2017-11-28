package web_service.order_servlet;

import identity_service.AccessToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ChatDriver")
public class ChatDriver extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccessToken access = new AccessToken();
        access.getCurrentToken((Integer) request.getSession().getAttribute("id"));
        if (AccessToken.isTokenExpiredInvalid(request, response)) {
            return;
        } else {
            AccessToken.updateAccessToken((Integer) request.getSession().getAttribute("id"), access);
        }
        HttpSession session = request.getSession();
        session.setAttribute("driverID",1);
        //session.setAttribute("driverId", request.getParameter("driverId"));
        response.sendRedirect(request.getContextPath() + "/order-chatcustomer.jsp");
    }
}
