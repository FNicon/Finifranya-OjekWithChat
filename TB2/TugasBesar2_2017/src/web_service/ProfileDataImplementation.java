package web_service;

import pojo.PojoList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@javax.jws.WebService(endpointInterface = "web_service.ProfileData")
public class ProfileDataImplementation implements ProfileData {
    @Override
    public PojoList getPreferredLocations(int id) {
        WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
        Connection connection = webServiceDbConnection.getConnection();
        ArrayList<String> locationData = new ArrayList<>();
        try {
            String query = "SELECT place FROM preferred_loc WHERE id=\"" + id + "\"";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String place = resultSet.getString("place");
                locationData.add(place);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return new PojoList(locationData);
    }
    @Override
    public void setIsDriver(int id) {
        WebServiceDbConnection webServiceDbConnection = new WebServiceDbConnection();
        Connection connection = webServiceDbConnection.getConnection();
        try {
            String query = "SELECT * FROM driver WHERE id=\"" + id + "\"";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {

            } else {
                query = "INSERT INTO driver (id,rating,votes,status) VALUES (\"" + id + "\",NULL,NULL,'isPassive')";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
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
