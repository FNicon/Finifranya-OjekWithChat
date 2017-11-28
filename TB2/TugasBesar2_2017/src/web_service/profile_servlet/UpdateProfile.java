package web_service.profile_servlet;

import identity_service.AccessToken;
import identity_service.IdentityServiceDbConnection;
import identity_service.User;
import web_service.WebServiceDbConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        AccessToken access = new AccessToken();
        access.getCurrentToken((Integer) request.getSession().getAttribute("id"));
        if (AccessToken.isTokenExpiredInvalid(request, response)) {
            return;
        } else {
            AccessToken.updateAccessToken((Integer) request.getSession()
                    .getAttribute("id"), access);
        }

        HttpSession session = request.getSession();

        if (!User.isUpdateDataValid(request.getParameter("name"), request
                .getParameter("phone"))) {
            session.setAttribute("errorMessage", true);

            response.sendRedirect(request.getContextPath() +
                    "/profile-editprofile.jsp");

            return;
        }

        IdentityServiceDbConnection identityServiceDbConnection = new
                IdentityServiceDbConnection();
        Connection connection = identityServiceDbConnection
                .getConnection();

        boolean isDriver = false;
        if (request.getParameter("status") != null) {
            isDriver = true;
        }

        try {
            String query = "UPDATE user SET name=?, phone=?, isDriver=? WHERE" +
                    " id=?";

            PreparedStatement preparedStatement = connection
                    .prepareStatement(query);
            preparedStatement.setString(1, request.getParameter("name"));
            preparedStatement.setString(2, request.getParameter("phone"));
            preparedStatement.setBoolean(3, isDriver);
            preparedStatement.setString(4, request.getParameter("id"));

            preparedStatement.execute();
            preparedStatement.close();
            session.setAttribute("name", request.getParameter("name"));
            session.setAttribute("phoneNumber", request.getParameter("phone"));
            session.setAttribute("isDriver", isDriver);
            setIsDriver(request.getParameter("id"));
            session.removeAttribute("errorMessage");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() +
                "/profile-editprofile.jsp");
    }
    public void setIsDriver(String id) {
        WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
        Connection connection = webServiceDbConnection.getConnection();
        try {
            String query = "SELECT * FROM driver WHERE id=\"" + id
                + "\"";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {

            } else {
                query = "INSERT INTO driver (id,rating,votes,status) VALUES (?,NULL,NULL,NULL)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, id);
                preparedStatement.execute();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
