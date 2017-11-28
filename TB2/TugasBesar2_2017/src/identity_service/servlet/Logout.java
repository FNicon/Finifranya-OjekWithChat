package identity_service.servlet;

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

@WebServlet(name = "Logout")
public class Logout extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        setDrivertoPassive((Integer) session.getAttribute("id"));
        AccessToken.removeAccessToken((AccessToken) session.getAttribute
                ("accessToken"));

        session.invalidate();

        session = request.getSession();
        if (request.getParameter("token_expired") != null) {
            session.setAttribute("errorMessageTokenExpired", true);
        } else if (request.getParameter("token_invalid") != null) {
            session.setAttribute("errorMessageTokenInvalid", true);
        }

        response.sendRedirect(request.getContextPath() + "/login.jsp");
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
