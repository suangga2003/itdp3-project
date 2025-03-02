INSERT INTO users (name, email, nik, address, phone_number)
VALUES
    ('John Doe', 'john.doe@example.com', '1234567890123456', '123 Main St', '123-456-7890'),
    ('Jane Smith', 'jane.smith@example.com', '6543210987654321', '456 Elm St', '987-654-3210'),
    ('Alice Johnson', 'alice.johnson@example.com', '1122334455667788', '789 Oak St', '555-123-4567');

INSERT INTO bank_users (user_id, account_number)
VALUES
    (1, '1234567890123456'), -- John Doe's account
    (2, '6543210987654321'), -- Jane Smith's account
    (3, '1122334455667788'); -- Alice Johnson's account

INSERT INTO currencies (code, name, currency_rate, is_primary)
VALUES
    (360, 'IDR', 1, TRUE), -- Indonesian Rupiah
    (840, 'USD', 16000, FALSE),   -- US Dollar
    (978, 'JPY', 100, FALSE);  -- Japanese Yen

INSERT INTO bank_balances (balance, bank_user_id, currency_id)
VALUES
    (1000000.0, 1, 1), -- John Doe's balance in USD
    (500.0, 1, 2),  -- John Doe's balance in EUR
    (500.0, 1, 3),  -- John Doe's balance in EUR
    (2000.0, 2, 1), -- Jane Smith's balance in USD
    (2000.0, 2, 2), -- Jane Smith's balance in USD
    (2000.0, 2, 3), -- Jane Smith's balance in USD
    (1500.0, 3, 1), -- Alice Johnson's balance in IDR
    (1500.0, 3, 2), -- Alice Johnson's balance in IDR
    (1500.0, 3, 3); -- Alice Johnson's balance in IDR