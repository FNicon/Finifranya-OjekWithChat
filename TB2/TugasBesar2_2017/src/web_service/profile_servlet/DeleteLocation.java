package web_service.profile_servlet;

import identity_service.AccessToken;
import web_service.WebServiceDbConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "DeleteLocation")
public class DeleteLocation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccessToken access = new AccessToken();
        access.getCurrentToken((Integer) request.getSession().getAttribute("id"));
        if (AccessToken.isTokenExpiredInvalid(request, response)) {
            return;
        } else {
            AccessToken.updateAccessToken((Integer) request.getSession().getAttribute("id"), access);
        }
        WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
        Connection connection = webServiceDbConnection.getConnection();
        try {
            String query = "DELETE FROM preferred_loc WHERE id=? AND place=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, request.getParameter("id"));
            preparedStatement.setString(2, request.getParameter("oldlocation"));
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/profile-editlocations.jsp");
    }
}
