var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/db_CHAT_SERVICE";
var config = {
    apiKey: "AIzaSyCc6vmHlFnhaPAJejk43UVelMX6F8Oh0Zw",
    authDomain: "tugas3-finifranya.firebaseapp.com",
    databaseURL: "https://tugas3-finifranya.firebaseio.com",
    projectId: "tugas3-finifranya",
    storageBucket: "tugas3-finifranya.appspot.com",
    messagingSenderId: "651746263430"
};
firebase.initializeApp(config);

MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  db.createCollection("chat", function(err, res) {
    if (err) throw err;
    console.log("Collection created!");
    db.close();
  });
  db.createCollection("driver_online", function(err, res) {
    if (err) throw err;
    console.log("Collection created!");
    db.close();
  });
});