package web_service;

import pojo.PojoList;

import javax.jws.WebMethod;

@javax.jws.WebService
public interface OrderData {
    @WebMethod
    public String getPreferredDriver(String origin, String destination, String username, int userId);

    @WebMethod
    public PojoList getAvailableDrivers(String origin, String destination, int userId);

    @WebMethod
    public String getProfilePicById(int id);

    @WebMethod
    public String getUsernameById(int id);

    @WebMethod
    public String getNameById(int id);

    @WebMethod
    public int getIdByUserName(String username);

    @WebMethod
    public int getDriverStatus(int id);

    @WebMethod
    public float getDriverRating(int id);

    @WebMethod
    public int getDriverVote(int id);
}
