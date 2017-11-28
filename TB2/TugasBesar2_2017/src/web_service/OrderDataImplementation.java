package web_service;

import identity_service.IdentityServiceDbConnection;
import org.json.JSONException;
import org.json.JSONObject;
import pojo.PojoList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@javax.jws.WebService(endpointInterface = "web_service.OrderData")
public class OrderDataImplementation implements OrderData {
    @Override
    public String getPreferredDriver(String origin, String username, int userId) {
        IdentityServiceDbConnection identityServiceDbConnection = new IdentityServiceDbConnection();
        Connection idConnection = identityServiceDbConnection.getConnection();
        WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
        Connection wsConnection = webServiceDbConnection.getConnection();
        int driverId = getIdByUserName(username);
        JSONObject driver = new JSONObject();
        try {
            String idQuery = "SELECT id, name, profilePicture FROM user WHERE" + " id='" + driverId + "' AND id<>'" + userId + "'";
            Statement idStatement = idConnection.createStatement();
            ResultSet idResultSet = idStatement.executeQuery(idQuery);
            while (idResultSet.next()) {
                String name = idResultSet.getString("name");
                String profilePic = idResultSet.getString("profilePicture");
                try {
                    String wsQuery = "SELECT AVG(order_rating) as rating, COUNT(order_id) as votes FROM orders " +
                            "JOIN preferred_loc ON (id_driver = id) WHERE id='" +
                            driverId + "' AND id<>'" + userId + "' AND " +
                            "place='" + origin + "'";
                    Statement wsStatement = wsConnection.createStatement();
                    ResultSet wsResultSet = wsStatement.executeQuery(wsQuery);
                    while (wsResultSet.next()) {
                        float rating = wsResultSet.getFloat("rating");
                        int votes = wsResultSet.getInt("votes");
                        try {
                            driver.put("id", driverId);
                            driver.put("name", name);
                            driver.put("profilePic", profilePic);
                            driver.put("rating", rating);
                            driver.put("votes", votes);
                        } catch (JSONException je) {
                            je.printStackTrace();
                        }
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return driver.toString();
    }

    @Override
    public PojoList getAvailableDrivers(String origin, int userId) {
        IdentityServiceDbConnection identityServiceDbConnection = new IdentityServiceDbConnection();
        Connection idConnection = identityServiceDbConnection.getConnection();
        WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
        Connection wsConnection = webServiceDbConnection.getConnection();
        ArrayList<String> drivers = new ArrayList<>();
        try {
            String idQuery = "SELECT id, name, profilePicture FROM user WHERE" +
                    " id<>'" + userId + "'";
            Statement idStatement = idConnection.createStatement();
            ResultSet idResultSet = idStatement.executeQuery(idQuery);
            while (idResultSet.next()) {
                int driverId = idResultSet.getInt("id");
                String name = idResultSet.getString("name");
                String profilePic = idResultSet.getString("profilePicture");
                try {
                    String wsQuery = "SELECT AVG(order_rating) as rating, COUNT(order_id) as votes FROM orders " +
                            "JOIN preferred_loc ON (id_driver = id) WHERE id='" +
                            driverId + "' AND id<>'" + userId + "' AND " +
                            "place='" + origin + "'";
                    Statement wsStatement = wsConnection.createStatement();
                    ResultSet wsResultSet = wsStatement.executeQuery(wsQuery);
                    while (wsResultSet.next()) {
                        float rating = wsResultSet.getFloat("rating");
                        int votes = wsResultSet.getInt("votes");
                        JSONObject driver = new JSONObject();
                        try {
                            driver.put("id", driverId);
                            driver.put("name", name);
                            driver.put("profilePic", profilePic);
                            driver.put("rating", rating);
                            driver.put("votes", votes);
                            drivers.add(driver.toString());
                        } catch (JSONException je) {
                            je.printStackTrace();
                        }
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return new PojoList(drivers);
    }

    @Override
    public String getProfilePicById(int id) {
        IdentityServiceDbConnection identityServiceDbConnection = new IdentityServiceDbConnection();
        Connection connection = identityServiceDbConnection.getConnection();
        String profilePic = "";
        try {
            String query = "SELECT profilePicture FROM user WHERE id='" + id + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                profilePic = resultSet.getString("profilePicture");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return profilePic;
    }

    @Override
    public String getUsernameById(int id) {
        IdentityServiceDbConnection identityServiceDbConnection = new IdentityServiceDbConnection();
        Connection connection = identityServiceDbConnection.getConnection();
        String username = "";
        try {
            String query = "SELECT username FROM user WHERE id='" + id + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                username = resultSet.getString("username");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return username;
    }

    @Override
    public String getNameById(int id) {
        IdentityServiceDbConnection identityServiceDbConnection = new IdentityServiceDbConnection();
        Connection connection = identityServiceDbConnection.getConnection();
        String name = "";
        try {
            String query = "SELECT name FROM user WHERE id='" + id + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return name;
    }

    @Override
    public int getIdByUserName(String username) {
        IdentityServiceDbConnection identityServiceDbConnection = new IdentityServiceDbConnection();
        Connection connection = identityServiceDbConnection.getConnection();
        int id = 0;
        try {
            String query = "SELECT id FROM user WHERE username='" + username + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return id;
    }

    @Override
    public int getDriverStatus(int id) {
        WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
        Connection connection = webServiceDbConnection.getConnection();
        String driverStatusNow = "NULL";
        int statusNow = 0;
        try {
            //driverStatusNow = session.getAttribut("statusDriver");
            String query = "SELECT status FROM driver WHERE id='" + id + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                driverStatusNow = resultSet.getString("status");
            }
            if (driverStatusNow == "isChatting") {
                statusNow = 2;
            } else if (driverStatusNow == "isFinding") {
                statusNow = 1;
            } else {
                statusNow = 0;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return (statusNow);
    }
}
