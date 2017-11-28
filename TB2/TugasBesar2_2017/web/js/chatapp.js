var chatapp = angular.module("chatapp",[]);

//TODO : bikin method post dan get ke server glassfish, to be added

chatapp.controller("ChatAppCtrl", function($scope, $http, $window) {
    $scope.newMessage = '';
    $scope.sendMessage = function() {
        if($scope.newMessage) {
            $scope.saveMessage(1,2,$scope.newMessage);
            console.log($scope.newMessage);
            $scope.newMessage = '';
        }
    }
    $scope.loadMessages = function() {
        $http.get("http://localhost:3000").then(function (messages) {
            $scope.ChatMessages = messages.data;
        })
    }
    $scope.saveMessage = function(sendId, recvId, msg) {
        $http.post("http://localhost:3000/newMsg", {
            sendId:sendId,
            recId:recvId,
            message:msg}).then(function(){
            $scope.loadMessages();
        })
    }
    $scope.loadMessages();
});