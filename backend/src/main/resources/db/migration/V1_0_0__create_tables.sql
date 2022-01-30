DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS product;

CREATE TABLE App_user (
    	user_id int8 NOT NULL AUTO_INCREMENT,
    	firstname varchar(255) NULL,
    	lastname varchar(255) NULL,
    	email varchar(255) NULL,
    	password varchar(255) NULL,
    	CONSTRAINT app_user_pkey PRIMARY KEY (user_id)
);

CREATE TABLE Product (
    	id int8 NOT NULL AUTO_INCREMENT,
    	name varchar(255) NULL,
    	CONSTRAINT product_pkey PRIMARY KEY (id)
);
