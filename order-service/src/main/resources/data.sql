
DROP TABLE IF EXISTS orders;  

create table orders
(
    order_id      INT AUTO_INCREMENT  PRIMARY KEY,
    order_code    VARCHAR(100),
    product_id    INTEGER,
    quantity      INTEGER,
    product_price DECIMAL(13, 2),
    user_id       INTEGER
);

insert into orders(order_id, order_code, product_id, quantity, product_price, user_id) VALUES (1, 'ORD_001', 1, 4, 3700, 1);
insert into orders(order_id, order_code, product_id, quantity, product_price, user_id) VALUES (2, 'ORD_002', 2, 3, 15000, 2);
insert into orders(order_id, order_code, product_id, quantity, product_price, user_id) VALUES (3, 'ORD_003', 9, 10, 15000, 9);

