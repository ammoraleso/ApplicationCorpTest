#First question
SELECT
    c.first_name AS customer_name,
    p.product_name AS product_purchased
FROM
    Customer c
        JOIN
    Order o ON c.customer_id = o.customer_id
        JOIN
    Product p ON o.order_id = p.order_id
ORDER BY
    c.first_name;

#Second question
SELECT
    c.first_name AS customer_name
FROM
    Customer c
        LEFT JOIN
    Ordere o ON c.customer_id = o.customer_id
WHERE
    o.customer_id IS NULL;

#Third question
SELECT
    c.first_name AS customer_name
FROM
    Custom c
        JOIN
    Order o ON c.customer_id = o.customer_id
        JOIN
    Product p ON o.order_id = p.order_id
GROUP BY
    c.customer_id, c.first_name
HAVING
    COUNT(p.product_id) > 1;
