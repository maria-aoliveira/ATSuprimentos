-- DROP SCHEMA cotacao;

CREATE SCHEMA cotacao;

-- cotacao.cotacao definition

-- Drop table

-- DROP TABLE cotacao.cotacao;

CREATE TABLE cotacao.cotacao (
	id bigint NOT NULL,
	data_cotacao timestamp NOT NULL,
	nome_fornecedor varchar(255) NOT NULL,
	nome_produto varchar(255) NOT NULL,
	id_produto int8 NOT NULL,
	preco numeric(19,2) NOT NULL,
	validade_cotacao timestamp NOT NULL,
	CONSTRAINT cotacao_pkey PRIMARY KEY (id)
);