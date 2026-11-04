
--Liquibase formatted sql

--changeset ravish:001-create-customers-table

CREATE TABLE customers (
                           id CHAR(36) NOT NULL,
                           first_name VARCHAR(100) NOT NULL,
                           last_name VARCHAR(100) NOT NULL,
                           email VARCHAR(255) NOT NULL,
                           mobile_number VARCHAR(20) NOT NULL,
                           created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                           updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        ON UPDATE CURRENT_TIMESTAMP(6),

                           CONSTRAINT pk_customers PRIMARY KEY (id),
                           CONSTRAINT uk_customers_email UNIQUE (email),
                           CONSTRAINT uk_customers_mobile UNIQUE (mobile_number)
);
