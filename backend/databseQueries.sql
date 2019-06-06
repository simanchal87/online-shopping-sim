CREATE TABLE category(

id IDENTITY,
name VARCHAR(50),
description VARCHAR(255),
image_url VARCHAR(50),
is_active boolean,

constraint pk_category_id primary key(id)

);
--DROP TABLE category;
--DROP ALL OBJECTS;

INSERT INTO category (name,description,image_url,is_active) 
VALUES('Laptop','Desc for Laptop','CAT_1.png','TRUE');

INSERT INTO category (name,description,image_url,is_active) 
VALUES('Mobile','Desc for Mobile','CAT_2.png','TRUE');

INSERT INTO category (name,description,image_url,is_active) 
VALUES('Television','Desc for Television','CAT_3.png','TRUE');


CREATE TABLE user_detail
(
id IDENTITY,
first_name VARCHAR(50),
last_name VARCHAR(50),
role VARCHAR(50),
enable boolean,
password VARCHAR(50),
email VARCHAR(100),
contact_number VARCHAR(15),
constraint pk_user_id primary key(id)

);

--DROP TABLE user_details;
--DROP ALL OBJECTS;

INSERT INTO user_detail (first_name,last_name,role,enable,password,email,contact_number) 
values ('Virat','Kholi','ADMIN',true,'admin','vk@gmail.com','8888888888');

INSERT INTO user_detail (first_name,last_name,role,enable,password,email,contact_number) 
values ('Sachin','Tendulkar','SUPPLIER',true,'12345','st@gmail.com','9999999999');

INSERT INTO user_detail (first_name,last_name,role,enable,password,email,contact_number) 
values ('MS','Dhoni','SUPPLIER',true,'12345','md@gmail.com','7777777777');


CREATE TABLE product
(
id IDENTITY,
code VARCHAR(50),
name VARCHAR(50),
brand VARCHAR(50),
description VARCHAR(255),
unit_price decimal(10,2),
quantity int,
is_active boolean,
category_id int,
supplier_id int,
purchases int DEFAULT 0,
views int DEFAULT 0,

constraint pk_product_id primary key(id),
constraint fk_product_category_id FOREIGN KEY (category_id) references category(id),
constraint fk_product_supplier_id FOREIGN KEY (supplier_id) references user_detail(id)

);


--DROP TABLE product;
--DROP ALL OBJECTS;

-- adding five products
INSERT INTO product 
(code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES 
('PRDABC123DEFX', 'iPhone 5s', 'apple', 'This is one of the best phone available in the market right now!', 18000, 5, true, 3, 2, 0, 0 );

INSERT INTO product 
(code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES 
('PRDDEF123DEFX', 'Samsung s7', 'samsung', 'A smart phone by samsung!', 32000, 2, true, 3, 3, 0, 0 );

INSERT INTO product 
(code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES 
('PRDPQR123WGTX', 'Google Pixel', 'google', 'This is one of the best android smart phone available in the market right now!', 57000, 5, true, 3, 2, 0, 0 );

INSERT INTO product 
(code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES 
('PRDMNO123PQRX', ' Macbook Pro', 'apple', 'This is one of the best laptops available in the market right now!', 54000, 3, true, 1, 2, 0, 0 );

INSERT INTO product 
(code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES 
('PRDABCXYZDEFX', 'Dell Latitude E6510', 'dell', 'This is one of the best laptop series from dell that can be used!', 48000, 5, true, 1, 3, 0, 0 );



CREATE TABLE address (
	id IDENTITY,
	user_id int,
	address_line_one VARCHAR(100),
	address_line_two VARCHAR(100),
	city VARCHAR(20),
	state VARCHAR(20),
	country VARCHAR(20),
	postal_code VARCHAR(10),
	is_billing BOOLEAN,
	is_shipping BOOLEAN,
	CONSTRAINT fk_address_user_id FOREIGN KEY (user_id ) REFERENCES user_detail (id),
	CONSTRAINT pk_address_id PRIMARY KEY (id)
);

-- adding a supplier correspondece address
INSERT INTO address
( user_id, address_line_one, address_line_two, city, state, country, postal_code, is_billing, is_shipping) 
VALUES
(4, '102 Sabarmati Society, Mahatma Gandhi Road', 'Near Salt Lake, Gandhi Nagar', 'Ahmedabad', 'Gujarat', 'India', '111111', true, false );


-- the address table to store the user billing and shipping addresses
CREATE TABLE address (
	id IDENTITY,
	user_id int,
	address_line_one VARCHAR(100),
	address_line_two VARCHAR(100),
	city VARCHAR(20),
	state VARCHAR(20),
	country VARCHAR(20),
	postal_code VARCHAR(10),
	is_billing BOOLEAN,
	is_shipping BOOLEAN,
	CONSTRAINT fk_address_user_id FOREIGN KEY (user_id ) REFERENCES user_detail (id),
	CONSTRAINT pk_address_id PRIMARY KEY (id)
);

-- the cart table to store the user cart top-level details
CREATE TABLE cart (
	id IDENTITY,
	user_id int,
	grand_total DECIMAL(10,2),
	cart_lines int,
	CONSTRAINT fk_cart_user_id FOREIGN KEY (user_id ) REFERENCES user_detail (id),
	CONSTRAINT pk_cart_id PRIMARY KEY (id)
);
-- the cart line table to store the cart details

-- adding a cart for testing 
INSERT INTO cart (user_id, grand_total, cart_lines) VALUES (4, 0, 0);

CREATE TABLE cart_line (
	id IDENTITY,
	cart_id int,
	total DECIMAL(10,2),
	product_id int,
	product_count int,
	buying_price DECIMAL(10,2),
	is_available boolean,
	CONSTRAINT fk_cartline_product_id FOREIGN KEY (product_id ) REFERENCES product (id),
	CONSTRAINT pk_cartline_id PRIMARY KEY (id)
);


-- the order detail table to store the order

CREATE TABLE order_detail (
	id IDENTITY,
	user_id int,
	order_total DECIMAL(10,2),
	order_count int,
	shipping_id int,
	billing_id int,
	order_date date,
	CONSTRAINT fk_order_detail_user_id FOREIGN KEY (user_id) REFERENCES user_detail (id),
	CONSTRAINT fk_order_detail_shipping_id FOREIGN KEY (shipping_id) REFERENCES address (id),
	CONSTRAINT fk_order_detail_billing_id FOREIGN KEY (billing_id) REFERENCES address (id),
	CONSTRAINT pk_order_detail_id PRIMARY KEY (id)
);

-- the order item table to store order items

CREATE TABLE order_item (
	id IDENTITY,
	order_id int,
	total DECIMAL(10,2),
	product_id int,
	product_count int,
	buying_price DECIMAL(10,2),
	CONSTRAINT fk_order_item_product_id FOREIGN KEY (product_id) REFERENCES product (id),
	CONSTRAINT fk_order_item_order_id FOREIGN KEY (order_id) REFERENCES order_detail (id),
	CONSTRAINT pk_order_item_id PRIMARY KEY (id)
);



















