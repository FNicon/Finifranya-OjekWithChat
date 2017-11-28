var express = require("express");
var mongoose = require("mongoose");
var bodyparser = require("body-parser");
var cors = require("cors");

var admin = require("firebase-admin");
var serviceAccount = require("/firebase/tugas3-finifranya-firebase-adminsdk-6mg86-3073d696ed.json");

admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://tugas3-finifranya.firebaseio.com"
});


mongoose.connect('mongodb://localhost/db_OJEK_CHAT');

//TODO : bikin method post dan get, sambungin ke firebase sama mongodb

var app = express();
app.use(cors());
app.use(bodyparser())

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

app.get("/", function(req, res) {
    Message.find(function(err,messages) {
        res.send(messages);
    })
});

app.post("/newMsg",function(req,res) {
    saveMessage(req.body.sendId,req.body.recId,req.body.message);
    Message.find(function(err,messages) {
        res.send(messages);
    })
})

app.listen(3000);