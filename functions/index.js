const functions = require('firebase-functions');

const admin = require('firebase-admin'); 
admin.initializeApp(); 

exports.addMessage = functions.https.onRequest(async(req, res) => {
  const original =  req.query.text; 

  const snapshot = await admin.database().ref('/messages').push({original:original}); 

  res.redirect(303, shapshot.ref.toString()); 
})

exports.addOnChange = functions.firestore
.document("/Tasks/{taskId}")
.onWrite((change, context) =>{
  const task = document
  const user = task.claimed; 
  task.message = "A change was Made" + Date; 

  const payload = {
    notification: {
      title: task.title + " was changed", 
      body: "Check the App to see what has happened!"
    }
  };

  return admin
  .firestore()
  .collection("Users")
  .get()
  // eslint-disable-next-line promise/always-return
  .then(Users =>{
     const deviceId = [];
     deviceId.push(Users.user.deviceId);
     deviceId.forEach(element => {
       admin.messaging().sendToDevice(element, payload);
     });
  })

});
// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });
