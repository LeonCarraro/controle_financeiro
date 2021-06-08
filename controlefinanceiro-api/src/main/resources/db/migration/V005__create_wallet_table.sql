CREATE TABLE tb_wallet (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR (50) NOT NULL,
    balance DECIMAL (12, 2) NOT NULL,
    user_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES tb_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
