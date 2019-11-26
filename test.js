// File intended only for testing on localhost
// Be sure to run 'npm install express' before attempting to launch

const express = require('express'), app = express();

app.use(express.static('public'))

app.get('/', function(req, res) {
  res.sendFile(__dirname + '/public/html/index.html');
});

app.get('/admin', function(req, res) {
  res.sendFile(__dirname + '/public/html/admin.html');
});

const listener = app.listen(process.env.PORT || 3000, function() {
  console.log('App listening on port ' + listener.address().port);
});