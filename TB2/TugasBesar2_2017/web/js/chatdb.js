var chatapp = angular.module("chatapp",[]);
var mongoose = require("mongoose");

mongoose.connect('mongodb://localhost/db_OJEK_CHAT');

var MessageSchema = new mongoose.Schema({
    sendId : Number,
    recId : Number,
    message : String,
});
var Message = mongoose.model('Message', MessageSchema);

function saveMessage(senderId, receiverId, messageStr) {
    var message = new Message({
        sendId : senderId,
        recId : receiverId,
        message : messageStr
    });

    message.save(function(err){
        if(err) {
            console.log('Save db failed');
        } else {
            console.log('Save db OK');
        }
    });
}