CREATE TABLE orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date_hour DATETIME NOT NULL,
    status ENUM(
        'COMPLETED',
        'CANCELED',
        'PAID',
        'NOT_AUTHORIZED',
        'CONFIRMED',
        'READY',
        'OUT_FOR_DELIVERY',
        'DELIVERED'
    ) NOT NULL,
    PRIMARY KEY (id)
);