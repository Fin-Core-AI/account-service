
--Liquibase formatted sql

--changeset ravish:002-create-accounts-table

CREATE TABLE accounts (
                          id CHAR(36) NOT NULL,
                          account_number VARCHAR(20) NOT NULL,
                          customer_id CHAR(36) NOT NULL,
                          account_type VARCHAR(20) NOT NULL,
                          status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
                          currency CHAR(3) NOT NULL DEFAULT 'INR',
                          created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                          updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
        ON UPDATE CURRENT_TIMESTAMP(6),

                          CONSTRAINT pk_accounts PRIMARY KEY (id),
                          CONSTRAINT uk_accounts_account_number UNIQUE (account_number),

                          CONSTRAINT fk_accounts_customer
                              FOREIGN KEY (customer_id)
                                  REFERENCES customers(id)
                                  ON DELETE RESTRICT,

                          INDEX idx_accounts_customer_id (customer_id)
);
