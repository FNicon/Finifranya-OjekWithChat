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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "OrderDriverStatusFinding")
public class OrderDriverStatusFinding extends HttpServlet {
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
    setDrivertoFinding((Integer) session.getAttribute("id"));
    session.setAttribute("driverStatus","isFinding");
    response.sendRedirect(request.getContextPath() + "/order-driverpassive.jsp");
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
  public void setDrivertoFinding(int id) {
    WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
    Connection connection = webServiceDbConnection.getConnection();
    try {
      String query = "UPDATE driver SET status=? WHERE id=?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, "isFinding");
      preparedStatement.setInt(2, id);
      preparedStatement.execute();
    } catch (SQLException se) {
      se.printStackTrace();
    }
  }
}
