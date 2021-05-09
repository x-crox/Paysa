require('dotenv').config();

const app = require('express')();
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const mysql = require('mysql');
const jwt = require('jsonwebtoken');
const nodemailer = require('nodemailer');

app.use(cookieParser());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended : true }));

const conn = mysql.createConnection({
	host: "localhost",
	user: process.env.MYSQL_USERNAME,
	password: process.env.MYSQL_PASSWORD,
	database: process.env.MYSQL_DATABASE	
});

const transporter = nodemailer.createTransport({
  service: 'gmail',
  auth: {
    user: process.env.PAYSA_USER,
    pass: process.env.PAYSA_PASS
  }
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

const validatejwt = (req, res, next) => {
	try {
	  let decoded = jwt.verify(req.cookies.token, process.env.JWT_SECRET);
	  req.userdata.email = decoded.email;
	} catch(err) {
	  throw err;
	}	
	next();
}

app.post('/signup', (req, res) => {
	console.log(req);
	res.status(200).json({err : false, msg : ""});
});

app.post('/signin', (req, res) => {
	console.log(req);
	res.status(200).json({err : false, msg : ""});
});

app.post('/getProfile', validatejwt, (req, res) => {
	conn.query('SELECT fullname, balance FROM Users WHERE email = ?', [req.userdata.email], (err, rows, fields) => {
	  if (err) {
	  	throw err;	
	  }
	  console.log(rows);
	  res.status(200).json({err : false});
	});	
});

app.use((err, req, res, next) => {
  console.log(err);
  res.status(501).json({err : true, msg : "something went wrong, check server logs"});
});