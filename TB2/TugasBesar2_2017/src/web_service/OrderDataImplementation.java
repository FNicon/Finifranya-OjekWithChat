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
    public String getPreferredDriver(String origin, String destination, String username, int userId) {
        IdentityServiceDbConnection identityServiceDbConnection = new IdentityServiceDbConnection();
        Connection idConnection = identityServiceDbConnection.getConnection();
        WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
        Connection wsConnection = webServiceDbConnection.getConnection();
        int driverId = getIdByUserName(username);
        JSONObject driver = new JSONObject();
        try {
            String idQuery = "SELECT id, name, profilePicture FROM user WHERE id='" + driverId + "' AND id<>'" + userId + "' AND isDriver=1";
            Statement idStatement = idConnection.createStatement();
            ResultSet idResultSet = idStatement.executeQuery(idQuery);
            while (idResultSet.next()) {
                String name = idResultSet.getString("name");
                String profilePic = idResultSet.getString("profilePicture");
                try {
                    String wsQuery = "SELECT id FROM driver NATURAL JOIN preferred_loc WHERE id='" +
                            driverId + "' AND id<>'" + userId + "' AND (place='" + origin + "' OR place='" + destination +"') AND status='isFinding'";
                    Statement wsStatement = wsConnection.createStatement();
                    ResultSet wsResultSet = wsStatement.executeQuery(wsQuery);
                    while (wsResultSet.next()) {
                        int filteredID = wsResultSet.getInt("id");
                        float rating = getDriverRating(filteredID);
                        int votes = getDriverVote(filteredID);
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
    public PojoList getAvailableDrivers(String origin, String destination, int userId) {
        IdentityServiceDbConnection identityServiceDbConnection = new IdentityServiceDbConnection();
        Connection idConnection = identityServiceDbConnection.getConnection();
        WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
        Connection wsConnection = webServiceDbConnection.getConnection();
        ArrayList<String> drivers = new ArrayList<>();
        try {
            String idQuery = "SELECT id, name, profilePicture FROM user WHERE id<>'" + userId + "' AND isDriver=1";
            Statement idStatement = idConnection.createStatement();
            ResultSet idResultSet = idStatement.executeQuery(idQuery);
            while (idResultSet.next()) {
                int driverId = idResultSet.getInt("id");
                String name = idResultSet.getString("name");
                String profilePic = idResultSet.getString("profilePicture");
                try {
                    String wsQuery = "SELECT id FROM driver NATURAL JOIN preferred_loc WHERE id='" + driverId
                        + "' AND id<>'" + userId + "' AND (place='" + origin + "' OR place='" + destination + "') AND status='isFinding'";
                    Statement wsStatement = wsConnection.createStatement();
                    ResultSet wsResultSet = wsStatement.executeQuery(wsQuery);
                    while (wsResultSet.next()) {
                        int filteredID = wsResultSet.getInt("id");
                        float rating = getDriverRating(filteredID);
                        int votes = getDriverVote(filteredID);
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
    @Override
    public float getDriverRating(int id) {
        WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
        Connection wsConnection = webServiceDbConnection.getConnection();
        float Rating = 0;
        int Votes = 0;
        try {
            //driverStatusNow = session.getAttribut("statusDriver");
            String query = "SELECT COUNT(order_id) as votes, AVG(order_rating) as rating FROM orders WHERE id_driver='"+id+"'";
            Statement statement = wsConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Votes = resultSet.getInt("votes");
                if (Votes==0) {
                    Rating = 0;
                } else {
                    Rating = resultSet.getFloat("rating");
                }
            }
            resultSet.close();
            statement.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return Rating;
    }
    @Override
    public int getDriverVote(int id) {
        WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
        Connection wsConnection = webServiceDbConnection.getConnection();
        int Vote = 0;
        try {
            //driverStatusNow = session.getAttribut("statusDriver");
            String query = "SELECT COUNT(order_id) as votes FROM orders WHERE id_driver='"+id+"'";
            Statement statement = wsConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Vote = resultSet.getInt("votes");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return Vote;
    }
}
