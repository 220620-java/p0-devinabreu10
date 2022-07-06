-- Overview of BankApp Tables --
create table users ( 
	user_id SERIAL primary key,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	username varchar(30) unique not null,
	passwrd varchar(30) not null
);

create table accounts (
	account_id SERIAL primary key,
	account_type varchar(20) not null,
	account_balance decimal(15, 2), -- decimal(m,n) m is precision and n is scale(places after decimal) -9999.99 - 9999.99
	user_id integer references users
);

/* create table transactions (
	transac_id SERIAL primary key,
	transac_type varchar(20),
	transac_balance decimal (15, 2),
	account_id integer references accounts
); */
