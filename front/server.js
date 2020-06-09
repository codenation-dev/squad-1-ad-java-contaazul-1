var connect = require("connect");

var app = connect().use(connect.static(__dirname + '/public'));

app.listen(8080);