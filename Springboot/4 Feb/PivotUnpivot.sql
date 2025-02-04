CREATE EXTENSION IF NOT EXISTS tablefunc;

Create Type pid_enum as (101,102,103,104)

drop table if exists orders;
CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
	customer_id INT,
	customer_name varchar(30),
    product_id INT,
    order_date DATE,
    order_amount NUMERIC
);

delete from orders;
INSERT INTO orders (customer_id,customer_name, product_id, order_date, order_amount) VALUES
(1,'Madhav Jha',101, '2025-01-01', 100.00),
(1,'Sanat Khotaraj',101, '2025-01-02', 150.00),
(2,'Karan Rajput',102, '2025-02-03', 100.00),
(1,'Madhav Jha',103, '2025-02-05', 150.00),
(1,'Madhav Jha',104, '2025-01-01', 200.00),
(2,'Karan Rajput',101, '2025-02-03', 250.00),
(3,'Jonny Dabre',102, '2025-04-04', 300.00),
(3,'Jonny Dabre',103, '2025-02-03', 250.00),
(3,'Jonny Dabre',101,'2025-04-04', 300.00),
(2,'Karan Rajput',104, '2025-02-03', 250.00),
(3,'Karan Rajput',102, '2025-04-04', 300.00);

select * from orders;

SELECT *
FROM crosstab(
  'SELECT customer_id, product_id, order_amount 
   FROM orders 
   ORDER BY customer_id, product_id',
  'SELECT DISTINCT product_id FROM orders ORDER BY product_id'
) AS ct(customer_id INT, product_101 NUMERIC, product_102 NUMERIC, product_103 NUMERIC, product_104 NUMERIC);

SELECT customer_id, 'product_101' AS product_id, product_101 AS order_amount
FROM (
    SELECT * FROM crosstab(
        'SELECT customer_id, product_id, order_amount 
         FROM orders 
         ORDER BY customer_id, product_id',
        'SELECT DISTINCT product_id FROM orders ORDER BY product_id'
    ) AS ct(customer_id INT, product_101 NUMERIC, product_102 NUMERIC, product_103 NUMERIC, product_104 NUMERIC)
) AS pivoted_data

