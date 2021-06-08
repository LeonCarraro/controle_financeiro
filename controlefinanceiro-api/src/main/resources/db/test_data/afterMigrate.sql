DELETE FROM tb_transaction;
DELETE FROM tb_category;
DELETE FROM tb_wallet;
DELETE FROM tb_user;

ALTER TABLE tb_transaction AUTO_INCREMENT = 1;
ALTER TABLE tb_category AUTO_INCREMENT = 1;
ALTER TABLE tb_wallet AUTO_INCREMENT = 1;
ALTER TABLE tb_user AUTO_INCREMENT = 1;

INSERT INTO tb_user (id, username, password) VALUES (1, 'LeonCarraro', '$2a$10$54u.2xLwL8UTuTzwHPaV7OuTcJk6wRLC/42RNtAHDHj3EHLWDP6V2');

INSERT INTO tb_category (id, name, user_id) VALUES (1, 'Lazer', 1);
INSERT INTO tb_category (id, name, user_id) VALUES (2, 'Alimentação', 1);
INSERT INTO tb_category (id, name, user_id) VALUES (3, 'Transporte', 1);
INSERT INTO tb_category (id, name, user_id) VALUES (4, 'Mercado', 1);
INSERT INTO tb_category (id, name, user_id) VALUES (5, 'Investimentos', 1);
INSERT INTO tb_category (id, name, user_id) VALUES (6, 'Saúde', 1);
INSERT INTO tb_category (id, name, user_id) VALUES (7, 'Outros', 1);

INSERT INTO tb_wallet (id, name, balance, user_id) VALUES (1, 'Bolso', 0.0, 1);

INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (1, 'Transação 01 - LeonCarraro', 200.0, 1, 1, 1, '2020-09-02');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (2, 'Transação 02 - LeonCarraro', -200.0, 1, 3, 1, '2020-09-02');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (3, 'Transação 03 - LeonCarraro', -200.0, 1, 3, 1, '2020-09-05');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (4, 'Transação 04 - LeonCarraro', 200.0, 1, 2, 1, '2020-10-02');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (5, 'Transação 05 - LeonCarraro', -200.0, 1, 4, 1, '2020-09-02');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (6, 'Transação 06 - LeonCarraro', -200.0, 1, 5, 1, '2020-09-02');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (7, 'Transação 07 - LeonCarraro', -200.0, 1, 6, 1, '2020-09-05');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (8, 'Transação 08 - LeonCarraro', -200.0, 1, 6, 1, '2020-10-02');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (9, 'Transação 09 - LeonCarraro', 200.0, 1, 6, 1, '2020-10-08');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (10, 'Transação 10 - LeonCarraro', 200.0, 1, 7, 1, '2020-10-09');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (11, 'Transação 11 - LeonCarraro', -200.0, 1, 2, 1, '2020-10-12');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (12, 'Transação 12 - LeonCarraro', 200.0, 1, 4, 1, '2020-10-12');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (13, 'Transação 13 - LeonCarraro', -200.0, 1, 6, 1, '2020-10-13');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (14, 'Transação 14 - LeonCarraro', 200.0, 1, 1, 1, '2020-10-15');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (15, 'Transação 15 - LeonCarraro', -200.0, 1, 1, 1, '2020-10-20');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (16, 'Transação 16 - LeonCarraro', 200.0, 1, 2, 1, '2020-11-01');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (17, 'Transação 17 - LeonCarraro', 200.0, 1, 4, 1, '2020-11-03');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (18, 'Transação 18 - LeonCarraro', -200.0, 1, 5, 1, '2020-11-04');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (19, 'Transação 19 - LeonCarraro', -200.0, 1, 6, 1, '2020-11-04');
INSERT INTO tb_transaction (id, description, value, wallet_id, category_id, user_id, insertion_date) VALUES (20, 'Transação 20 - LeonCarraro', 200.0, 1, 7, 1, '2020-11-04');
