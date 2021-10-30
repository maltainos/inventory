CREATE TABLE IF NOT EXISTS role_access_level(
	role_id BIGINT NOT NULL,
    access_level_id BIGINT NOT NULL,
    FOREIGN KEY(role_id) REFERENCES roles(codigo),
    FOREIGN KEY(access_level_id) REFERENCES access_level(codigo)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;