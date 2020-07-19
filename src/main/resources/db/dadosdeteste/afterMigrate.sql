#Desabilita as Fks
SET FOREIGN_KEY_CHECKS=0;

#Limpo as Tabelas
delete from tb_cidade;
delete from tb_cozinha;
delete from tb_estado;
delete from tb_forma_pagamento;
delete from tb_grupo;
delete from tb_grupo_permissao;
delete from tb_permissao;
delete from tb_produto;
delete from tb_restaurante;
delete from tb_restaurante_forma_pagamento;
delete from tb_usuario;
delete from tb_usuario_grupo;

#Reabilita as Fks
SET FOREIGN_KEY_CHECKS=1;

#Seta O AutoIncremento igual a 1 Para todas as Tabelas
alter table tb_cozinha auto_increment=1;
alter table tb_cidade auto_increment=1;
alter table tb_estado auto_increment=1;
alter table tb_forma_pagamento auto_increment=1;
alter table tb_grupo auto_increment=1;
alter table tb_permissao auto_increment=1;
alter table tb_produto auto_increment=1;
alter table tb_restaurante auto_increment=1;
alter table tb_usuario auto_increment=1;

#Inserindo Dados de Cozinha
insert into tb_cozinha(nome) values ('Tailandesa');
insert into tb_cozinha(nome) values ('Indiana');
insert into tb_cozinha(nome) values ('Argentina');
insert into tb_cozinha(nome) values ('Brasileira');

#Inserindo Dados de Estado
insert into tb_estado (nome) values ('Minas Gerais');
insert into tb_estado (nome) values ('São Paulo');
insert into tb_estado (nome) values ('Ceará');

#Inserindo Dados da Cidade
insert into tb_cidade (nome, id_estado) values ('Uberlândia', 1);
insert into tb_cidade (nome, id_estado) values ('Belo Horizonte', 1);
insert into tb_cidade (nome, id_estado) values ('São Paulo', 2);
insert into tb_cidade (nome, id_estado) values ('Campinas', 2);
insert into tb_cidade (nome, id_estado) values ('Fortaleza', 3);

#Inserindo Dados na Forma de Pagamento
insert into tb_forma_pagamento (descricao) values ('Cartão de crédito');
insert into tb_forma_pagamento (descricao) values ('Cartão de débito');
insert into tb_forma_pagamento (descricao) values ('Dinheiro');

#Inserindo Dados de Restaurante
insert into tb_restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao ,ativo ) values ('Thai Delivery', 9.50 , 2, utc_timestamp, utc_timestamp, true);
insert into tb_restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao ,ativo) values ('Tuk Tuk Comida Indiana', 15,2 , utc_timestamp , utc_timestamp, true);
insert into tb_restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, data_cadastro, data_atualizacao ,ativo) values ('Thai Gourmet', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', utc_timestamp ,utc_timestamp, true);
insert into tb_restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao ,ativo) values ('Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true);
insert into tb_restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao ,ativo) values ('Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true);
insert into tb_restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao ,ativo) values ('Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true);

#Inserindo Dados de Produtos
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

#Inserindo Permissões
insert into tb_permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tb_permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

#Inserindo Dados de Formas de Pagamento para os Restaurantes
insert into tb_restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

#Inserindo a Tabela de Grupo
insert into tb_grupo (nome) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

#Inserindo a Tabela de Usuários
insert into tb_usuario (id, nome, email, senha, data_cadastro) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp);