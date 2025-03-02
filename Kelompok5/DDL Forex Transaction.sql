
DROP TABLE IF EXISTS user_profiles CASCADE;
DROP TABLE IF EXISTS user_authentications CASCADE;
DROP TABLE IF EXISTS currencies CASCADE;
DROP TABLE IF EXISTS user_accounts CASCADE;
DROP TABLE IF EXISTS exchange_rates CASCADE;
DROP TABLE IF EXISTS transaction_histories CASCADE;

CREATE TABLE user_profiles (
  id SERIAL PRIMARY KEY,
  fullname VARCHAR NOT NULL,
  gender VARCHAR NOT NULL,
  place_of_birth VARCHAR NOT NULL,
  date_of_birth DATE NOT NULL,
  address VARCHAR NOT NULL,
  province VARCHAR NOT NULL,
  city VARCHAR NOT NULL,
  district VARCHAR NOT NULL,
  subdistrict VARCHAR NOT NULL,
  postal_code VARCHAR NOT NULL,
  identity_type VARCHAR NOT NULL,
  identity_number VARCHAR NOT NULL,
  phone_number VARCHAR NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP
);


CREATE TABLE user_authentications (
  id SERIAL PRIMARY KEY,
  user_profile_id INT REFERENCES user_profiles(id),
  email VARCHAR NOT NULL,
  password VARCHAR NOT NULL,
  pin VARCHAR NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE currencies (
  id SERIAL PRIMARY KEY,
  code VARCHAR UNIQUE NOT NULL,
  description VARCHAR NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE user_accounts (
  id SERIAL PRIMARY KEY,
  user_profile_id INT REFERENCES user_profiles(id),
  currency_code VARCHAR REFERENCES currencies(code),
  account_number VARCHAR NOT NULL,
  balance DECIMAL DEFAULT 0.00,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE exchange_rates (
  id SERIAL PRIMARY KEY,
  from_currency_code VARCHAR REFERENCES currencies(code),
  to_currency_code VARCHAR REFERENCES currencies(code),
  exchange_rate FLOAT NOT NULL,
  rate_date DATE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE transaction_histories (
  id SERIAL PRIMARY KEY,
  from_user_account_id INT REFERENCES user_accounts(id),
  dest_user_account_id INT REFERENCES user_accounts(id),
  transaction_date TIMESTAMP,
  from_trans_amount DECIMAL DEFAULT 0.00,
  dest_trans_amount DECIMAL DEFAULT 0.00,
  from_currency VARCHAR REFERENCES currencies(code),
  dest_currency VARCHAR REFERENCES currencies(code),
  exchange_rate FLOAT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP
);



INSERT INTO "public"."currencies" VALUES (1, 'IDR', 'Indonesian Rupiah', '2025-02-28 20:47:32.679795', NULL);
INSERT INTO "public"."currencies" VALUES (2, 'USD', 'United States Dollar ', '2025-02-28 20:48:26.539573', NULL);
INSERT INTO "public"."currencies" VALUES (3, 'AUD', 'Australian Dollar', '2025-02-28 20:49:33.652717', NULL);
INSERT INTO "public"."currencies" VALUES (4, 'GBP', 'Pounds Sterling', '2025-02-28 20:51:04.94757', NULL);
INSERT INTO "public"."currencies" VALUES (5, 'EUR', 'Euro', '2025-02-28 20:51:36.066656', NULL);
INSERT INTO "public"."currencies" VALUES (6, 'RMB', 'Yuan', '2025-02-28 20:52:28.344641', NULL);
INSERT INTO "public"."currencies" VALUES (7, 'RUB', 'Ruble', '2025-02-28 20:52:58.29897', NULL);
INSERT INTO "public"."currencies" VALUES (8, 'SGD', 'Singapore Dollar', '2025-02-28 20:53:29.749293', NULL);
INSERT INTO "public"."currencies" VALUES (9, 'TRY', 'Lira', '2025-02-28 20:54:12.710832', NULL);
INSERT INTO "public"."currencies" VALUES (10, 'AED', 'UAE Dirham', '2025-02-28 20:54:35.738037', NULL);
INSERT INTO "public"."currencies" VALUES (11, 'BND', 'Brunei Darussalam Dollar', '2025-02-28 20:55:25.833031', NULL);
INSERT INTO "public"."currencies" VALUES (12, 'PHP', 'Peso', '2025-02-28 20:55:47.252522', NULL);
INSERT INTO "public"."currencies" VALUES (13, 'INR', 'Indian Rupee', '2025-02-28 20:56:06.948323', NULL);
INSERT INTO "public"."currencies" VALUES (14, 'JPY', 'Yen', '2025-02-28 20:56:28.52026', NULL);
INSERT INTO "public"."currencies" VALUES (15, 'KRW', 'Won', '2025-02-28 20:57:19.704775', NULL);


INSERT INTO "public"."exchange_rates" VALUES (1, 'IDR', 'USD', 0.0000604678, '2025-03-01', '2025-03-01 07:54:29.719936', NULL);


INSERT INTO "public"."user_profiles" VALUES (1, 'Anthony', 'MALE', 'Manchester', '1991-07-18', 'Jl Kapten Tendean no 49', 'Jawa Barat', 'Bandung', 'Kota Bandung', 'HegarManah', '12345', 'KTP', '123456789', '08125437823', '2025-02-28 21:17:37.530611', NULL);
INSERT INTO "public"."user_profiles" VALUES (12, 'string', 'string', 'string', '2024-01-01', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', '2025-03-02 14:55:48.291349', NULL);
INSERT INTO "public"."user_profiles" VALUES (14, 'string', 'string', 'string', '2024-01-01', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', '2025-03-02 15:14:28.152328', NULL);
INSERT INTO "public"."user_profiles" VALUES (15, 'string', 'string', 'string', '2022-01-01', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', '2025-03-02 15:28:59.790487', NULL);
INSERT INTO "public"."user_profiles" VALUES (18, 'string', 'string', 'string', '2022-01-02', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', '2025-03-02 16:20:45.546932', NULL);
INSERT INTO "public"."user_profiles" VALUES (2, 'suyou', 'MALE', 'Tangerang', '2000-10-20', 'Jl Praja VI no 61', 'DI Jakarta', 'Jakarta', 'Gambir', 'Gambir', '155505', 'KTP', '28713891738', '082134091202', '2025-02-28 21:18:43.977868', '2025-03-02 22:07:27.690289');


INSERT INTO "public"."user_authentications" VALUES (1, 1, 'Anthony18@gmail.com', 'elgasing', '123456', '2025-02-28 21:23:58', NULL);
INSERT INTO "public"."user_authentications" VALUES (10, 12, 'string', 'string', 'string', '2025-03-02 14:55:50.325459', NULL);
INSERT INTO "public"."user_authentications" VALUES (11, 14, 'strings', 'string', 'string', '2025-03-02 15:14:48.038026', NULL);
INSERT INTO "public"."user_authentications" VALUES (12, 15, 'string@test', 'string', 'string', '2025-03-02 15:28:59.919265', NULL);
INSERT INTO "public"."user_authentications" VALUES (13, 18, 'string@eee', 'string', 'string', '2025-03-02 16:20:47.293584', NULL);
INSERT INTO "public"."user_authentications" VALUES (2, 2, 'Laylabeban@gmail.com', 'Pass@123', '132456', '2025-02-28 21:24:41.775579', '2025-03-02 22:07:27.690289');


INSERT INTO "public"."user_accounts" VALUES (3, 1, 'JPY', '89120001', 9000, '2025-02-28 21:22:06.105995', NULL);
INSERT INTO "public"."user_accounts" VALUES (4, 2, 'IDR', '13253253', 2000000, '2025-02-28 21:22:27.452279', NULL);
INSERT INTO "public"."user_accounts" VALUES (5, 2, 'USD', '82371823', 50000, '2025-02-28 21:22:45.260604', NULL);
INSERT INTO "public"."user_accounts" VALUES (6, 2, 'JPY', '43726347', 890000, '2025-02-28 21:22:59.024755', NULL);
INSERT INTO "public"."user_accounts" VALUES (1, 1, 'IDR', '12345678', 57200, '2025-02-28 21:21:00.865804', '2025-03-01 17:55:46.462322');
INSERT INTO "public"."user_accounts" VALUES (2, 1, 'USD', '34764283', 50407.77587792, '2025-02-28 21:21:44.775634', '2025-03-01 17:55:46.462322');
INSERT INTO "public"."user_accounts" VALUES (9, 14, 'IDR', 'string', 0, '2025-03-02 15:14:29.52941', NULL);
INSERT INTO "public"."user_accounts" VALUES (10, 15, 'IDR', 'string', 0, '2025-03-02 15:28:59.916041', NULL);
INSERT INTO "public"."user_accounts" VALUES (13, 18, 'IDR', 'string', 0, '2025-03-02 16:20:46.818458', NULL);


INSERT INTO "public"."transaction_histories" VALUES (1, 1, 2, '2025-03-01 17:55:46.345812', 1000, 0.06034, 'IDR', 'USD', 6.034e-05, '2025-03-01 17:55:46.345812', NULL);


INSERT INTO "public"."user_accounts" VALUES (3, 1, 'JPY', '89120001', 9000, '2025-02-28 21:22:06.105995', NULL);
INSERT INTO "public"."user_accounts" VALUES (4, 2, 'IDR', '13253253', 2000000, '2025-02-28 21:22:27.452279', NULL);
INSERT INTO "public"."user_accounts" VALUES (5, 2, 'USD', '82371823', 50000, '2025-02-28 21:22:45.260604', NULL);
INSERT INTO "public"."user_accounts" VALUES (6, 2, 'JPY', '43726347', 890000, '2025-02-28 21:22:59.024755', NULL);
INSERT INTO "public"."user_accounts" VALUES (1, 1, 'IDR', '12345678', 57200, '2025-02-28 21:21:00.865804', '2025-03-01 17:55:46.462322');
INSERT INTO "public"."user_accounts" VALUES (2, 1, 'USD', '34764283', 50407.77587792, '2025-02-28 21:21:44.775634', '2025-03-01 17:55:46.462322');
INSERT INTO "public"."user_accounts" VALUES (9, 14, 'IDR', 'string', 0, '2025-03-02 15:14:29.52941', NULL);
INSERT INTO "public"."user_accounts" VALUES (10, 15, 'IDR', 'string', 0, '2025-03-02 15:28:59.916041', NULL);
INSERT INTO "public"."user_accounts" VALUES (13, 18, 'IDR', 'string', 0, '2025-03-02 16:20:46.818458', NULL);