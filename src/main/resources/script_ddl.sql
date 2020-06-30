create table tb_cidade (id bigint not null auto_increment, nome varchar(255) not null, id_estado bigint not null, primary key (id)) engine=InnoDB
create table tb_cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, id_cozinha bigint not null, endereco_id_cidade bigint, primary key (id)) engine=InnoDB
create table tb_usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_grupo_permissao (id_grupo bigint not null, id_permissao bigint not null) engine=InnoDB
create table tb_produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, id_restaurante bigint not null, primary key (id)) engine=InnoDB
create table tb_restaurante_forma_pagamento (id_restaurante bigint not null, id_forma_pagamento bigint not null) engine=InnoDB
create table tb_usuario_grupo (id_usuario bigint not null, id_grupo bigint not null) engine=InnoDB
alter table tb_cidade add constraint FK12tfv6wlsvaml7nsmq106hvib foreign key (id_estado) references tb_estado (id)
alter table tb_restaurante add constraint FKfg14jluyhafigqvd9o3ac1fv6 foreign key (id_cozinha) references tb_cozinha (id)
alter table tb_restaurante add constraint FKjpcylarpo3w23si3ug762jo2j foreign key (endereco_id_cidade) references tb_cidade (id)
alter table tb_grupo_permissao add constraint FKmfgfk2ucarrjqhu186pfp778x foreign key (id_permissao) references tb_permissao (id)
alter table tb_grupo_permissao add constraint FKgsf3vw2cqjbdw2veifvl35f1f foreign key (id_grupo) references tb_grupo (id)
alter table tb_produto add constraint FKesnslp1am9ticebt0dw7feuul foreign key (id_restaurante) references tb_restaurante (id)
alter table tb_restaurante_forma_pagamento add constraint FKlm4jr5rina9tf2tfivhr76ucy foreign key (id_forma_pagamento) references tb_forma_pagamento (id)
alter table tb_restaurante_forma_pagamento add constraint FKr6sv0ukvwx9phf2fe6lm5s3ug foreign key (id_restaurante) references tb_restaurante (id)
alter table tb_usuario_grupo add constraint FKqxy9455xabfm1m4e88a5kqa1l foreign key (id_grupo) references tb_grupo (id)
alter table tb_usuario_grupo add constraint FK33trkg7gm3nmi37hpqv4lofek foreign key (id_usuario) references tb_usuario (id)
create table tb_cidade (id bigint not null auto_increment, nome varchar(255) not null, id_estado bigint not null, primary key (id)) engine=InnoDB
create table tb_cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, id_cozinha bigint not null, endereco_id_cidade bigint, primary key (id)) engine=InnoDB
create table tb_usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table tb_grupo_permissao (id_grupo bigint not null, id_permissao bigint not null) engine=InnoDB
create table tb_produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, id_restaurante bigint not null, primary key (id)) engine=InnoDB
create table tb_restaurante_forma_pagamento (id_restaurante bigint not null, id_forma_pagamento bigint not null) engine=InnoDB
create table tb_usuario_grupo (id_usuario bigint not null, id_grupo bigint not null) engine=InnoDB
alter table tb_cidade add constraint FK12tfv6wlsvaml7nsmq106hvib foreign key (id_estado) references tb_estado (id)
alter table tb_restaurante add constraint FKfg14jluyhafigqvd9o3ac1fv6 foreign key (id_cozinha) references tb_cozinha (id)
alter table tb_restaurante add constraint FKjpcylarpo3w23si3ug762jo2j foreign key (endereco_id_cidade) references tb_cidade (id)
alter table tb_grupo_permissao add constraint FKmfgfk2ucarrjqhu186pfp778x foreign key (id_permissao) references tb_permissao (id)
alter table tb_grupo_permissao add constraint FKgsf3vw2cqjbdw2veifvl35f1f foreign key (id_grupo) references tb_grupo (id)
alter table tb_produto add constraint FKesnslp1am9ticebt0dw7feuul foreign key (id_restaurante) references tb_restaurante (id)
alter table tb_restaurante_forma_pagamento add constraint FKlm4jr5rina9tf2tfivhr76ucy foreign key (id_forma_pagamento) references tb_forma_pagamento (id)
alter table tb_restaurante_forma_pagamento add constraint FKr6sv0ukvwx9phf2fe6lm5s3ug foreign key (id_restaurante) references tb_restaurante (id)
alter table tb_usuario_grupo add constraint FKqxy9455xabfm1m4e88a5kqa1l foreign key (id_grupo) references tb_grupo (id)
alter table tb_usuario_grupo add constraint FK33trkg7gm3nmi37hpqv4lofek foreign key (id_usuario) references tb_usuario (id)
