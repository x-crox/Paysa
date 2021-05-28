CREATE DATABASE PAYSA;

DROP TABLE IF EXISTS `GroupMembers`;
DROP TABLE IF EXISTS `PersonalExpense`;
DROP TABLE IF EXISTS `Groups`;
DROP TABLE IF EXISTS `GroupMembers`;
DROP TABLE IF EXISTS `PaymentTracking`;
DROP TABLE IF EXISTS `Users`;
DROP TABLE IF EXISTS `OTP`;
DROP TABLE IF EXISTS `Income`;
DROP TABLE IF EXISTS `Expense`;

CREATE TABLE Users (
	email VARCHAR(50),
	fullname VARCHAR(50),
	password VARCHAR(50),
	balance NUMERIC(5, 2),
	PRIMARY KEY (email)
);

CREATE TABLE Groups (
	group_name VARCHAR(50),
	PRIMARY KEY (group_name)
);

CREATE TABLE GroupMembers (
	group_name VARCHAR(50),
	email VARCHAR(50),
	PRIMARY KEY (group_name, email),
	FOREIGN KEY (group_name) REFERENCES Groups(group_name),
	FOREIGN KEY (email) REFERENCES Users(email)
);

CREATE TABLE PaymentTracking (
	group_name VARCHAR(50),
	payor VARCHAR(50),
	payee VARCHAR(50),
	amount NUMERIC(10, 2)
);

CREATE TABLE OTP (
	email VARCHAR(50),
	otp VARCHAR(50),
	PRIMARY KEY (email)
);

CREATE TABLE Income (
   	email VARCHAR(50),
    amount NUMERIC(10, 2),
    income_date DATE
);

CREATE TABLE Expense (
   	email VARCHAR(50),
   	category VARCHAR(50),
    amount NUMERIC(10, 2),
    expense_date DATE
);

INSERT INTO Users VALUES('nairrvarsha@gmail.com', 'Varsha Nair', '123', 0.0);
INSERT INTO Users VALUES('atrimfire@gmail.com', 'Atrim Mukherjee', '123', 0.0);
INSERT INTO Users VALUES('vighneshsnayak@gmail.com', 'Vighnesh Nayak S', '123', 0.0);
INSERT INTO Users VALUES('rhagav@gmail.com', 'Rhagav', '123', 0.0);
INSERT INTO Users VALUES('bhargav@gmail.com', 'Bhargav', '123', 0.0);
INSERT INTO Users VALUES('shwetha@gmail.com', 'Shwetha', '123', 0.0);
INSERT INTO Users VALUES('disha@gmail.com', 'Disha', '123', 0.0);
INSERT INTO Users VALUES('anirudh@gmail.com', 'Anirudh', '123', 0.0);
INSERT INTO Users VALUES('varun@gmail.com', 'Varun', '123', 0.0);
INSERT INTO Users VALUES('sathvik@gmail.com', 'Sathvik', '123', 0.0);
INSERT INTO Users VALUES('shamanth@gmail.com', 'Shamanth', '123', 0.0);
INSERT INTO Users VALUES('vidya@gmail.com', 'Vidya', '123', 0.0);
INSERT INTO Users VALUES('shruthi@gmail.com', 'Shruthi', '123', 0.0);
INSERT INTO Users VALUES('malvika@gmail.com', 'Malvika', '123', 0.0);

INSERT INTO Income VALUES('nairrvarsha@gmail.com', 100.0, '2021-01-05');
INSERT INTO Income VALUES('nairrvarsha@gmail.com', 100.0, '2021-02-05');
INSERT INTO Income VALUES('nairrvarsha@gmail.com', 100.0, '2021-03-05');
INSERT INTO Income VALUES('nairrvarsha@gmail.com', 100.0, '2021-04-05');
INSERT INTO Income VALUES('nairrvarsha@gmail.com', 100.0, '2021-05-05');
INSERT INTO Income VALUES('nairrvarsha@gmail.com', 100.0, '2021-06-05');
INSERT INTO Income VALUES('nairrvarsha@gmail.com', 100.0, '2021-07-05');
INSERT INTO Income VALUES('nairrvarsha@gmail.com', 100.0, '2021-08-05');
INSERT INTO Income VALUES('nairrvarsha@gmail.com', 100.0, '2021-09-05');
INSERT INTO Income VALUES('nairrvarsha@gmail.com', 100.0, '2021-10-05');
INSERT INTO Income VALUES('nairrvarsha@gmail.com', 100.0, '2021-11-05');
INSERT INTO Income VALUES('nairrvarsha@gmail.com', 100.0, '2021-12-05');

INSERT INTO Expense VALUES('nairrvarsha@gmail.com', 'food', 100.0, '2021-01-05');
INSERT INTO Expense VALUES('nairrvarsha@gmail.com', 'food', 100.0, '2021-02-05');
INSERT INTO Expense VALUES('nairrvarsha@gmail.com', 'rent', 100.0, '2021-03-05');
INSERT INTO Expense VALUES('nairrvarsha@gmail.com', 'groceries', 100.0, '2021-04-05');
INSERT INTO Expense VALUES('nairrvarsha@gmail.com', 'rent', 100.0, '2021-05-05');
INSERT INTO Expense VALUES('nairrvarsha@gmail.com', 'rent', 100.0, '2021-06-05');
INSERT INTO Expense VALUES('nairrvarsha@gmail.com', 'rent', 100.0, '2021-07-05');
INSERT INTO Expense VALUES('nairrvarsha@gmail.com', 'rent', 100.0, '2021-08-05');
INSERT INTO Expense VALUES('nairrvarsha@gmail.com', 'transport', 100.0, '2021-09-05');
INSERT INTO Expense VALUES('nairrvarsha@gmail.com', 'transport', 100.0, '2021-10-05');
INSERT INTO Expense VALUES('nairrvarsha@gmail.com', 'electricity', 100.0, '2021-11-05');
INSERT INTO Expense VALUES('nairrvarsha@gmail.com', 'food', 100.0, '2021-12-05');

INSERT INTO Income VALUES('atrimfire@gmail.com', 100.0, '2021-01-05');
INSERT INTO Income VALUES('atrimfire@gmail.com', 100.0, '2021-02-05');
INSERT INTO Income VALUES('atrimfire@gmail.com', 100.0, '2021-03-05');
INSERT INTO Income VALUES('atrimfire@gmail.com', 100.0, '2021-04-05');
INSERT INTO Income VALUES('atrimfire@gmail.com', 100.0, '2021-05-05');
INSERT INTO Income VALUES('atrimfire@gmail.com', 100.0, '2021-06-05');
INSERT INTO Income VALUES('atrimfire@gmail.com', 100.0, '2021-07-05');
INSERT INTO Income VALUES('atrimfire@gmail.com', 100.0, '2021-08-05');
INSERT INTO Income VALUES('atrimfire@gmail.com', 100.0, '2021-09-05');
INSERT INTO Income VALUES('atrimfire@gmail.com', 100.0, '2021-10-05');
INSERT INTO Income VALUES('atrimfire@gmail.com', 100.0, '2021-11-05');
INSERT INTO Income VALUES('atrimfire@gmail.com', 100.0, '2021-12-05');

INSERT INTO Expense VALUES('atrimfire@gmail.com', 'food', 100.0, '2021-01-05');
INSERT INTO Expense VALUES('atrimfire@gmail.com', 'food', 100.0, '2021-02-05');
INSERT INTO Expense VALUES('atrimfire@gmail.com', 'rent', 100.0, '2021-03-05');
INSERT INTO Expense VALUES('atrimfire@gmail.com', 'groceries', 100.0, '2021-04-05');
INSERT INTO Expense VALUES('atrimfire@gmail.com', 'rent', 100.0, '2021-05-05');
INSERT INTO Expense VALUES('atrimfire@gmail.com', 'rent', 100.0, '2021-06-05');
INSERT INTO Expense VALUES('atrimfire@gmail.com', 'rent', 100.0, '2021-07-05');
INSERT INTO Expense VALUES('atrimfire@gmail.com', 'rent', 100.0, '2021-08-05');
INSERT INTO Expense VALUES('atrimfire@gmail.com', 'transport', 100.0, '2021-09-05');
INSERT INTO Expense VALUES('atrimfire@gmail.com', 'transport', 100.0, '2021-10-05');
INSERT INTO Expense VALUES('atrimfire@gmail.com', 'electricity', 100.0, '2021-11-05');
INSERT INTO Expense VALUES('atrimfire@gmail.com', 'food', 100.0, '2021-12-05');

INSERT INTO Income VALUES('vighneshsnayak@gmail.com', 100.0, '2021-01-05');
INSERT INTO Income VALUES('vighneshsnayak@gmail.com', 100.0, '2021-02-05');
INSERT INTO Income VALUES('vighneshsnayak@gmail.com', 100.0, '2021-03-05');
INSERT INTO Income VALUES('vighneshsnayak@gmail.com', 100.0, '2021-04-05');
INSERT INTO Income VALUES('vighneshsnayak@gmail.com', 100.0, '2021-05-05');
INSERT INTO Income VALUES('vighneshsnayak@gmail.com', 100.0, '2021-06-05');
INSERT INTO Income VALUES('vighneshsnayak@gmail.com', 100.0, '2021-07-05');
INSERT INTO Income VALUES('vighneshsnayak@gmail.com', 100.0, '2021-08-05');
INSERT INTO Income VALUES('vighneshsnayak@gmail.com', 100.0, '2021-09-05');
INSERT INTO Income VALUES('vighneshsnayak@gmail.com', 100.0, '2021-10-05');
INSERT INTO Income VALUES('vighneshsnayak@gmail.com', 100.0, '2021-11-05');
INSERT INTO Income VALUES('vighneshsnayak@gmail.com', 100.0, '2021-12-05');

INSERT INTO Expense VALUES('vighneshsnayak@gmail.com', 'food', 100.0, '2021-01-05');
INSERT INTO Expense VALUES('vighneshsnayak@gmail.com', 'food', 100.0, '2021-02-05');
INSERT INTO Expense VALUES('vighneshsnayak@gmail.com', 'rent', 100.0, '2021-03-05');
INSERT INTO Expense VALUES('vighneshsnayak@gmail.com', 'groceries', 100.0, '2021-04-05');
INSERT INTO Expense VALUES('vighneshsnayak@gmail.com', 'rent', 100.0, '2021-05-05');
INSERT INTO Expense VALUES('vighneshsnayak@gmail.com', 'rent', 100.0, '2021-06-05');
INSERT INTO Expense VALUES('vighneshsnayak@gmail.com', 'rent', 100.0, '2021-07-05');
INSERT INTO Expense VALUES('vighneshsnayak@gmail.com', 'rent', 100.0, '2021-08-05');
INSERT INTO Expense VALUES('vighneshsnayak@gmail.com', 'transport', 100.0, '2021-09-05');
INSERT INTO Expense VALUES('vighneshsnayak@gmail.com', 'transport', 100.0, '2021-10-05');
INSERT INTO Expense VALUES('vighneshsnayak@gmail.com', 'electricity', 100.0, '2021-11-05');
INSERT INTO Expense VALUES('vighneshsnayak@gmail.com', 'food', 100.0, '2021-12-05');


# search by email
SELECT COUNT(*) AS valid
FROM Users
WHERE email = 'nairrvarsha@gmail.com';

# add new group member
INSERT INTO GroupMembers VALUES ('ccea', 'nairrvarsha@gmail.com');

# to display all the group that user belongs to
SELECT group_name
FROM GroupMembers
WHERE email = 'nairrvarsha@gmail.com';

# to display all group members
SELECT email
FROM GroupMembers
WHERE group_name = 'ccea';

# group payout (loop for all if equal spliting)
INSERT INTO PaymentTracking VALUES ('ccea', 'nairrvarsha@gmail.com', 'vighneshsnayak@gmail.com', 500.00);

# find how much A owes B (X)
SELECT SUM(amount) AS amount 
FROM PaymentTracking
WHERE payor = "nairrvarsha@gmail.com" AND payee = "vighneshsnayak@gmail.com";

# find how much B owes A (Y)
SELECT SUM(amount) AS amount
FROM PaymentTracking
WHERE payor = "vighneshsnayak@gmail.com" AND payee = "nairrvarsha@gmail.com";

# X - Y = 0 (sqaured off), X - Y > 0 (Y owes you) else X owes Y