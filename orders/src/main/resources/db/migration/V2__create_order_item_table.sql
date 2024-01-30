CREATE TABLE order_item (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) DEFAULT NULL,
    quantity INT NOT NULL,
    orders_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (orders_id) REFERENCES orders(id)
);