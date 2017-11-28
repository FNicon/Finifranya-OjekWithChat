package web_service;

import javax.xml.ws.Endpoint;

public class WebServicePublisher {
  public static void main(String[] args) {
    Endpoint.publish("http://localhost:9999/web_war_exploded/ProfileDataImplementationService?wsdl", new ProfileDataImplementation());
    Endpoint.publish("http://localhost:9999/web_war_exploded/OrderDataImplementationService?wsdl", new OrderDataImplementation());
    Endpoint.publish("http://localhost:9999/web_war_exploded/HistoryDataImplementationService?wsdl", new HistoryDataImplementation());
  }
}
