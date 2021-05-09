require('dotenv').config();

const app = require('express')();
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const mysql = require('mysql');

app.use(cookieParser());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended : true }));

const conn = mysql.createConnection({
	host: "localhost",
	user: process.env.MYSQL_USERNAME,
	password: process.env.MYSQL_PASSWORD,
	database: process.env.MYSQL_DATABASE	
});

conn.connect();

conn.query('SELECT 1 + 1 AS solution', (err, res, fields) => {
  if (err) {
  	throw err;	
  }
  console.log("connected to database");
});

app.listen(process.env.PORT, (err) => {
  if (err) {
  	throw err;
  }
  console.log(`Listening on port ${process.env.PORT}`);
});

app.use(function (err, req, res, next) {
  console.log(err);
  res.status(501).json({"res" : false, "msg" : "something went wrong, check server logs"});
});