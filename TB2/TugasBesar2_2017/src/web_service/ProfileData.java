package web_service;

import pojo.PojoList;

import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;

@javax.jws.WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ProfileData {
    @WebMethod
    public PojoList getPreferredLocations(int id);
    @WebMethod
    public void setIsDriver(int id);
    @WebMethod
    public float getDriverRating(int id);
    @WebMethod
    public int getDriverVote(int id);
}
