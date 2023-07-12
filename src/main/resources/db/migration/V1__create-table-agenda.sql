CREATE TABLE agenda (
    id bigint auto_increment,
    nome varchar(100),
    email varchar(100),
    telefone varchar(20),
    rua varchar(100),
    numero varchar(50),
    complemento varchar(50),
    bairro varchar(50),
    cidade varchar(50),
    uf varchar(50),
    ativo tinyint,
    primary key(id)
);