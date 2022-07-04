
DROP TABLE IF EXISTS product;  

create table product
(
    product_id         	INT AUTO_INCREMENT  PRIMARY KEY,
    code    	VARCHAR(100),
    name   		VARCHAR(100),
    description VARCHAR(100),
    category  	VARCHAR(100),
    price	   	DECIMAL(13, 2),
    quantity    INTEGER
);

insert into product(product_id, code, name, description, category, price, quantity) VALUES (1, 'P001', 'Samsung', 'Samsung mobile description', 'Mobile', 10000, 300);
insert into product(product_id, code, name, description, category, price, quantity) VALUES (2, 'P002', 'Samsung', 'Samsung TV description', 'TV', 5000, 200);
insert into product(product_id, code, name, description, category, price, quantity) VALUES (3, 'P003', 'LG', 'LG TV description', 'TV', 12500, 300);
insert into product(product_id, code, name, description, category, price, quantity) VALUES (4, 'P004', 'SONY', 'SONY TV description', 'TV', 17000, 0);
insert into product(product_id, code, name, description, category, price, quantity) VALUES (5, 'P005', 'NOKIA', 'NOKIA mobile description', 'Mobile', 2000, 50);
insert into product(product_id, code, name, description, category, price, quantity) VALUES (6, 'P006', 'MI', 'MI mobile description', 'Mobile', 11500, 70);
