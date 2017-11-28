package web_service.order_servlet;

import identity_service.AccessToken;
import web_service.WebServiceDbConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "OrderDriverStatusPassive")
public class OrderDriverStatusPassive extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    AccessToken access = new AccessToken();
    access.getCurrentToken((Integer) request.getSession().getAttribute("id"));
    if (AccessToken.isTokenExpiredInvalid(request, response)) {
      return;
    } else {
      AccessToken.updateAccessToken((Integer) request.getSession()
          .getAttribute("id"), access);
    }
    HttpSession session = request.getSession();
    setDrivertoPassive((Integer) session.getAttribute("id"));
    session.setAttribute("driverStatus","isPassive");
    response.sendRedirect(request.getContextPath() + "/order-driverpassive.jsp");
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  public void setDrivertoPassive(Integer id) {
    WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
    Connection connection = webServiceDbConnection.getConnection();
    try {
      String query = "UPDATE driver SET status=? WHERE id=?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, "isPassive");
      preparedStatement.setInt(2, id);
      preparedStatement.execute();
    } catch (SQLException se) {
      se.printStackTrace();
    }
  }
}
