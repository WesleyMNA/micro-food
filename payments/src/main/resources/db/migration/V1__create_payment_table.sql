CREATE TABLE payment (
    id BIGINT NOT NULL AUTO_INCREMENT,
    value DECIMAL(19,2) NOT NULL,
    name VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL,
    expiration VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    status ENUM('CREATED', 'CONFIRMED', 'CANCELED') NOT NULL,
    order_id BIGINT NOT NULL,
    payment_type_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
