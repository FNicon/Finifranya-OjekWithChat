<%--
    Created by IntelliJ IDEA.
    User: Finiko
    Date: 11/22/2017
    Time: 3:00 PM
    To change this template use File | Settings | File Templates.
--%>
<%@ include file="include/common.jsp" %>

<%@ page import="web_service.OrderData" %>
<%@ page import="web_service.WebService" %>
<%@ page import="javax.xml.ws.Service" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Order</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/orderDriver.css">
    <link rel="stylesheet" href="css/chat.css">
</head>
<body>
    <div class="container">
        <%
            out.println(printHeader((String) session.getAttribute("username")));
            out.println(printNavbar("order"));
        %>
        <div class="section">
            <div class="section-header">
                <div class="section-title">
                    LOOKING FOR AN ORDER
                </div>
            </div>
        </div>
        <%
            Service service = WebService.getService("9999", "OrderData");
            OrderData orderData = service.getPort(OrderData.class);

            int id = (Integer) session.getAttribute("id");
            //int statusDriverOrder = orderData.getDriverStatus(id);
            int statusDriverOrder;
            if (session.getAttribute("driverStatus") ==  "isChatting") {
              statusDriverOrder = 2;
            } else if (session.getAttribute("driverStatus") == "isFinding") {
              statusDriverOrder = 1;
            } else {
              statusDriverOrder = 0;
            }
            String nameTest = orderData.getNameById(id);
        %>
        <%if(statusDriverOrder == 0) { %>
            <div class = "passivePage">
                <form action="OrderDriverStatusFinding" method="post">
                    <input class="centerButtonFind" type="submit" value="FIND ORDER">
                </form>
            </div>
        <% } else if(statusDriverOrder == 1) { %>
            <div class="findingPage">
                <p class="textFindingWaiting">Finding Order....</p>
                <form action="OrderDriverStatusPassive" method="post">
                    <input class="centerButtonCancel" type="submit" value="CANCEL">
                </form>
            </div>
        <% } else if (statusDriverOrder == 2) { %>
            <div class="chattingPage">
                <p class="textNotifOrdering">Got an Order!</p>
                <p class="textUsernameOrdering"><% out.print(nameTest);%></p>
                <div class = "chatFrame">
                    <iframe width="100%" src="chatpage.html" frameBorder="0" seamless="seamless"></iframe>
                    <jsp:include page="chat-footer.jsp"/>
                </div>
            </div>
        <% } %>
    </div>
    <script src="js/orderDriverStatus.js"></script>
</body>
</html>
