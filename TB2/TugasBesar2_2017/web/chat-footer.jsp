<%--
  Created by IntelliJ IDEA.
  User: Finiko
  Date: 11/24/2017
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<div ng-app="chatapp" ng-controller="ChatAppCtrl" class="chatFooterFrame">
<form>
    <input class="chatInput" type="text" ng-model="newMessage" name="newMessage">
    <input class="chatButton" type="button" ng-click="sendMessage()" value="Kirim">
</form>
<script src="js/angular.js"></script>
<script src="js/chatapp.js"></script>
</div>
