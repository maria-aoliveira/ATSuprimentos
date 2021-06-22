-- DROP SCHEMA produto;

CREATE SCHEMA produto;

-- produto.produto definition

-- Drop table

-- DROP TABLE produto.produto;

CREATE TABLE produto.produto (
	id bigint NOT NULL,
	categoria varchar(255) NULL,
	foto varchar(255) NULL,
	nome varchar(255) NULL,
	CONSTRAINT produto_pkey PRIMARY KEY (id)
);