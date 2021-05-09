CREATE DATABASE PAYSA;

DROP TABLE IF EXISTS `GroupMembers`;
DROP TABLE IF EXISTS `PersonalExpense`;
DROP TABLE IF EXISTS `Group`;
DROP TABLE IF EXISTS `Users`;

CREATE TABLE Users (
	email VARCHAR(50),
	fullname VARCHAR(50),
	password VARCHAR(50),
	balance NUMERIC(5, 2),
	PRIMARY KEY (email)
);

CREATE TABLE PersonalExpense (
	p_id INT AUTO_INCREMENT,
	email VARCHAR(50),
	description VARCHAR(50),
	amount NUMERIC(5, 2),
	PRIMARY KEY (p_id),
	FOREIGN KEY (email) REFERENCES Users(email)
);

CREATE TABLE Groups (
	g_id INT AUTO_INCREMENT,
	name VARCHAR(50),
	PRIMARY KEY (g_id)
);

CREATE TABLE GroupMembers (
	g_id INT,
	email VARCHAR(50),
	owes NUMERIC(5, 2),
	paid NUMERIC(5, 2),
	PRIMARY KEY (g_id, email),
	FOREIGN KEY (g_id) REFERENCES Groups(g_id),
	FOREIGN KEY (email) REFERENCES Users(email)
);