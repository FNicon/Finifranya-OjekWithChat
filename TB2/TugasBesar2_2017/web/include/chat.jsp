<%--
  Created by IntelliJ IDEA.
  User: Finiko
  Date: 11/22/2017
  Time: 3:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body ng-app="chatapp" ng-controller="ChatAppCtrl as chatApp">
    <script>
        var id = session.getAttribute("id");
    </script>
    <div class="chatFrame">
        <div class="chatSection" ng-repeat="message in ChatMessages">
            {{message.message}}
            <p ng-if="message.sendId == 1" class="chatSelf">{{message.message}}</p>
            <p ng-if="message.recId == 1" class="chatOther">{{message.message}}</p>
        </div>
        <form class="chatFooterFrame">
            <input class="chatInput" type="text">
            <input class="chatButton" type="button" value="Kirim">
        </form>
    </div>
    <script src="../js/angular.js"></script>
    <script src="../js/chatapp.js"></script>
</body>
</html>
