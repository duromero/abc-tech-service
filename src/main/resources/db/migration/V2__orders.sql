CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT NOT NULL,
    operator_id BIGINT NOT NULL,
    start_order_location_id BIGINT NULL,
    end_order_location_id BIGINT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE orders_services (
    order_id BIGINT NOT NULL,
    services_id BIGINT NOT NULL,
    CONSTRAINT FK_ORDER_ASSISTANCE_ID
        FOREIGN KEY (services_id) REFERENCES assistances (id),
    CONSTRAINT FK_ASSISTANCE_ORDER_ID
        FOREIGN KEY (order_id) REFERENCES orders (id)
);

CREATE TABLE order_location (
    id BIGINT AUTO_INCREMENT NOT NULL,
    latitude DOUBLE NULL,
    longitude DOUBLE NULL,
    date DATETIME NULL,
    CONSTRAINT pk_orderlocation PRIMARY KEY (id)
);

ALTER TABLE orders ADD CONSTRAINT FK_ORDERS_ON_END_ORDER_LOCATION
    FOREIGN KEY (end_order_location_id) REFERENCES order_location (id);

ALTER TABLE orders ADD CONSTRAINT FK_ORDERS_ON_START_ORDER_LOCATION
    FOREIGN KEY (start_order_location_id) REFERENCES order_location (id);
