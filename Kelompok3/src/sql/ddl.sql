DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS bank_balances;
DROP TABLE IF EXISTS currencies;
DROP TABLE IF EXISTS bank_users;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    email VARCHAR(32) NOT NULL,
    nik VARCHAR(16) NOT NULL,
    address VARCHAR(32) NOT NULL,
    phone_number VARCHAR(16) NOT NULL,
    created_at TIMESTAMP NOT NULL default current_timestamp,
    updated_at TIMESTAMP NOT NULL default current_timestamp,
    deleted_at TIMESTAMP
);

CREATE TABLE bank_users (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    account_number VARCHAR(16) NOT NULL,
    created_at TIMESTAMP NOT NULL default current_timestamp,
    updated_at TIMESTAMP NOT NULL default current_timestamp,
    deleted_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE currencies (
    id BIGSERIAL PRIMARY KEY,
    code INT NOT NULL,
    name VARCHAR(32),
    currency_rate FLOAT,
    is_primary BOOLEAN,
    created_at TIMESTAMP NOT NULL default current_timestamp,
    updated_at TIMESTAMP NOT NULL default current_timestamp,
    deleted_at TIMESTAMP
);

CREATE TABLE bank_balances (
    id BIGSERIAL PRIMARY KEY,
    balance FLOAT,
    bank_user_id BIGINT NOT NULL,
    currency_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL default current_timestamp,
    updated_at TIMESTAMP NOT NULL default current_timestamp,
    deleted_at TIMESTAMP,
    FOREIGN KEY (bank_user_id) REFERENCES bank_users(id),
    FOREIGN KEY (currency_id) REFERENCES currencies(id)
);

CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    bank_user_id BIGINT NOT NULL,
    sell_currency_id BIGINT NOT NULL,
    buy_currency_id BIGINT NOT NULL,
    currency_rate FLOAT,
    start_value FLOAT,
    result_value BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL default current_timestamp,
    updated_at TIMESTAMP NOT NULL default current_timestamp,
    deleted_at TIMESTAMP,
    FOREIGN KEY (bank_user_id) REFERENCES bank_users(id),
    FOREIGN KEY (sell_currency_id) REFERENCES currencies(id),
    FOREIGN KEY (buy_currency_id) REFERENCES currencies(id)
);