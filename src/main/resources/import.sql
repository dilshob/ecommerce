DROP TABLE IF EXISTS users;
CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));



INSERT INTO users(username,password,enabled) VALUES ('arun','pass@123', true);
INSERT INTO users(username,password,enabled) VALUES ('deepak','pass@123', true);
INSERT INTO users(username,password,enabled) VALUES ('ravi','pass@123', true);
INSERT INTO users(username,password,enabled) VALUES ('karthik','pass@123', true);
INSERT INTO users(username,password,enabled) VALUES ('praveen','pass@123', true);


INSERT INTO user_roles (username, role) VALUES ('arun', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('deepak', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('ravi', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('karthik', 'ROLE_CUSTOMER');
INSERT INTO user_roles (username, role) VALUES ('praveen', 'ROLE_VISITOR');



INSERT INTO Product(product_name, product_desc, category, images_url, price, price_with_tax, tax_rate,quantity, width, 
height, depth, weight, shipping_fee) VALUES
('T-Shirt-Polo','cotton','Dress-Men','http://nourl1',	100,105,5,100,44,60,4,5,10),
('Shirt-Denim','cotton','Dress-Women','http://nourl2',	100,105,5,100,44,60,4,5,10),
('Shirt','cotton','Dress-Men','http://nourl3',			100,105,5,100,44,60,4,5,10),
('Toy-Car','cotton','Electronics','http://nourl3',			100,105,5,100,44,60,4,5,10),
('Jeans','cotton','Dress-Women','http://nourl3',			100,105,5,100,44,60,4,5,10),
('Photo-Frame','cotton','Electronics','http://nourl3',			100,105,5,100,44,60,4,5,10);



INSERT INTO purchase_details(product_id, quantity, final_price, purchase_date) VALUES
(2,1,300, null),
(5,2,400, null);