ALTER TABLE tb_category ADD COLUMN user_id BIGINT NOT NULL;
ALTER TABLE tb_category ADD FOREIGN KEY (user_id) REFERENCES tb_user (id);
