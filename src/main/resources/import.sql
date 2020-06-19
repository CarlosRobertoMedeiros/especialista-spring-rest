//Inserindo Dados de Cozinha
insert into tb_cozinha(nome) values ('Tailandesa');
insert into tb_cozinha(nome) values ('Indiana');

//Inserindo Dados de Restaurante
insert into tb_restaurante (nome, taxa_frete, id_cozinha) values ('Thai Gourmet', 10,2);
insert into tb_restaurante (nome, taxa_frete, id_cozinha) values ('Thai Delivery', 9.50 , 2);
insert into tb_restaurante (nome, taxa_frete, id_cozinha) values ('Tuk Tuk Comida Indiana', 15,2);

//Inserindo Dados de Estado
insert into tb_estado (nome) values ('Minas Gerais');
insert into tb_estado (nome) values ('São Paulo');
insert into tb_estado (nome) values ('Ceará');

//Inserindo Dados da Cidade
insert into tb_cidade (nome, id_estado) values ('Uberlândia', 1);
insert into tb_cidade (nome, id_estado) values ('Belo Horizonte', 1);
insert into tb_cidade (nome, id_estado) values ('São Paulo', 2);
insert into tb_cidade (nome, id_estado) values ('Campinas', 2);
insert into tb_cidade (nome, id_estado) values ('Fortaleza', 3);

//Inserindo Dados na Forma de Pagamento
insert into tb_forma_pagamento (descricao) values ('Cartão de crédito');
insert into tb_forma_pagamento (descricao) values ('Cartão de débito');
insert into tb_forma_pagamento (descricao) values ('Dinheiro');

//Inserindo Permissões
insert into tb_permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tb_permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');