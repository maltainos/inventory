CREATE TABLE pessoas(
	codigo BIGINT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    apelido VARCHAR(20) NOT NULL,
    ativo BOOLEAN,
    logradouro VARCHAR(75),
    numero INT,
    bairro VARCHAR(50),
    cep VARCHAR(15),
    cidade VARCHAR(30),
    estado VARCHAR(30)
);

INSERT INTO pessoas(nome, apelido, ativo) VALUES('Nelson', 'Albino', TRUE);
INSERT INTO pessoas
	(nome, apelido, ativo, logradouro, numero, bairro, cep, cidade, estado) 
	VALUES
	('Atija', 'Zaona', FALSE, 'Bairro dos Flores', 453, 'Inguri', '33.212-12', 'Angoche', 'MG');
	
INSERT INTO pessoas(nome, apelido, ativo) VALUES('Inocencia', 'Joao', FALSE);

INSERT INTO pessoas
	(nome, apelido, ativo, logradouro, numero, bairro, cep, cidade, estado) 
VALUES
	('Rony Zaona', 'Joao', FALSE, 'Bairro dos Flores', 453, 'Inguri', '33.212-12', 'Angoche', 'MG');
	
INSERT INTO pessoas(nome, apelido, ativo) VALUES('Ana', 'Maria', TRUE);

INSERT INTO pessoas
	(nome, apelido, ativo, logradouro, numero, bairro, cep, cidade, estado) 
VALUES
	('Abdul Omar', 'Mario', FALSE, 'Bairro dos Flores', 453, 'Inguri', '33.212-12', 'Angoche', 'MG');
	
INSERT INTO pessoas(nome, apelido, ativo) VALUES('Esperanca', 'Joao', FALSE);

INSERT INTO pessoas
	(nome, apelido, ativo, logradouro, numero, bairro, cep, cidade, estado) 
VALUES
	('Filomena', 'Zaona', FALSE, 'Bairro dos Flores', 453, 'Inguri', '33.212-12', 'Angoche', 'MG');
	
INSERT INTO pessoas(nome, apelido, ativo) VALUES('Inovancai', 'Zaona', TRUE);

INSERT INTO pessoas
	(nome, apelido, ativo, logradouro, numero, bairro, cep, cidade, estado) 
VALUES
	('Rony', 'Zaona', FALSE, 'Bairro dos Flores', 453, 'Inguri', '33.212-12', 'Angoche', 'MG');




