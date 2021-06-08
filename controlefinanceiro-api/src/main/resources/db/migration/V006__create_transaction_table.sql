CREATE TABLE tb_transaction (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR (100) NOT NULL,
    value DECIMAL (12, 2) NOT NULL,
    wallet_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (wallet_id) REFERENCES tb_wallet (id),
    FOREIGN KEY (category_id) REFERENCES tb_category (id),
    FOREIGN KEY (user_id) REFERENCES tb_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
