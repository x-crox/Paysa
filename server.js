require('dotenv').config();

const app = require('express')();
const bodyParser = require('body-parser');
const mysql = require('mysql');
const nodemailer = require('nodemailer');
const randomstring = require("randomstring");

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended : true }));

const conn = mysql.createConnection({
	host: "localhost",
	user: process.env.MYSQL_USERNAME,
	password: process.env.MYSQL_PASSWORD,
	database: process.env.MYSQL_DATABASE	
});

let transporter = nodemailer.createTransport({
  service: 'gmail',
  host: 'smtp.gmail.com',
  port: 587,
  secure: false,
  secureConnection: false,
  requireTLS: true,
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

app.get('/test', (req, res) => {
    res.status(200).json({
      team: "paysa",
      members: ["varsha", "atrim", "vighnesh"]
    });
});

app.post('/signup', (req, res) => {
	conn.query('SELECT * FROM Users WHERE email = ?', [req.body.email], (err, rows, fields) => {
	  if (err) {
	  	console.log(err);
	  	res.status(501).json({err : true, msg : "something went wrong, check server logs"});
	  } else {
		  if (rows.length === 0) {
			  let otp = randomstring.generate({length: 6, charset: 'numeric'});
				let mailOptions = {
			    from: process.env.PAYSA_USER,
			    to: req.body.email,
			    subject: 'Paysa registration',
			    text: 'Paysa registration OTP : ' + otp
				};	  
				transporter.sendMail(mailOptions, (err, info) => {
				  if (err) {
				    console.log(err);
				    res.status(501).json({err : true, msg : "something went wrong, check server logs"});
				  } else {
						conn.query('INSERT INTO OTP VALUES (?, ?)', [req.body.email, otp], (err, rows, fields) => {
						  if (err) {
						  	console.log(err);
						  	res.status(501).json({err : true, msg : "something went wrong, check server logs"});
						  } else {
						  	res.status(200).json({err : false, msg : ""});
						  } 
						});
				  }		  
				});
		  } else {
		  	res.status(200).json({err : true, msg : "account already exists"});
		  }
	  }	  	  
	});	
});

app.post('/verifyOTP', (req, res) => {
	conn.query('SELECT otp FROM OTP WHERE email = ?', [req.body.email], (err, rows, fields) => {
	  if (err) {
	  	console.log(err);
	  	res.status(501).json({err : true, msg : "something went wrong, check server logs"});
	  } else {
		  if (req.body.otp == rows[0].otp) {
				conn.query('INSERT INTO Users VALUES (?, ?, ?, ?)', [req.body.email, req.body.fullname, req.body.password, 0.00], (err, rows, fields) => {
				  if (err) {
				  	console.log(err);
				  	res.status(501).json({err : true, msg : "something went wrong, check server logs"});
				  } else {
				  	res.status(200).json({err : false, msg : ""});
				  }
				});		  	  	
		  } else {
		  	res.status(200).json({err : true, msg : "auth failed"});
		  }
	  }
	});	
});

app.post('/signin', (req, res) => {
	conn.query('SELECT COUNT(*) AS cnt FROM Users WHERE email = ? AND password = ?', [req.body.email, req.body.password], (err, rows, fields) => {
	  if (err) {
	  	console.log(err);	
	  	res.status(501).json({err : true, msg : "something went wrong, check server logs"});
	  } else {
		  if (rows[0].cnt === 1) {
				res.status(200).json({err : false, auth: true, msg : ""});
		  } else {
		  	res.status(401).json({err : false, auth: false, msg : "auth failed"});
		  }
	  }
	});
});

app.use((err, req, res, next) => {
  console.log(err);
  res.status(501).json({err : true, msg : "something went wrong, check server logs"});
});
