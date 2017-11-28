<%--
  Created by IntelliJ IDEA.
  User: Finiko
  Date: 11/23/2017
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="javax.xml.ws.Service" %>
<%@ page import="web_service.WebService" %>
<%@ page import="web_service.OrderData" %>

<%@ include file="include/common.jsp" %>
<%@ include file="include/orderHeaderCustomer.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NGEEENG! - A Solution for Your Transportation</title>
    <link rel="stylesheet" href="css/chat.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/orderDriver.css">
</head>
<body>
    <div class="container">
        <%
            out.println(printHeader((String) session.getAttribute("username")));
            out.println(printNavbar("order"));
        %>
        <div class="section">
            <%
                out.println(printOrderHeaderCustomer("ChatDriver"));
                Service service = WebService.getService("9999", "OrderData");
                OrderData orderData = service.getPort(OrderData.class);
                int driverId = (Integer) session.getAttribute("driverID");
            %>
        </div>
        <div class = "chatFrame">
            <iframe width="100%" src="chatpage.html" frameBorder="0" seamless="seamless"></iframe>
            <jsp:include page="chat-footer.jsp"/>
        </div>
        <form action="CompleteOrder" method="post">
            <input type="hidden" name="origin" value="${sessionScope.origin}">
            <input type="hidden" name="destination" value="${sessionScope.destination}">
            <input type="hidden" name="userId" value="${sessionScope.userId}">
            <input type="hidden" name="driverId" value=<% out.println(driverId); %>>
            <input class="centerButtonCancel" type="submit" value="CLOSE">
        </form>
        <!--a class="centerButtonCancel" href="order-complete.jsp">CLOSE</a-->
    </div>
</body>
</html>
