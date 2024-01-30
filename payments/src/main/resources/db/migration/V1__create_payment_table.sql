CREATE TABLE payment (
    id BIGINT NOT NULL AUTO_INCREMENT,
    value DECIMAL(19,2) NOT NULL,
    name VARCHAR(255),
    expiration VARCHAR(255),
    code VARCHAR(255),
    status ENUM('PENDING', 'COMPLETED', 'FAILED') NOT NULL,
    order_id BIGINT NOT NULL,
    payment_type_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
