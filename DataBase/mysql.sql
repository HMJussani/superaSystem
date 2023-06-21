create database dbSupera;

use dbSupera;

create table tbusuarios(
iduser int primary key auto_increment,
usuario varchar(50) not null,
login varchar(20) not null unique,
senha varchar(20) not null,
perfil varchar(20) not null

);

insert into tbusuarios (iduser ,usuario ,login ,senha ,perfil)values(2,'Administrador', 'admin', 'admin', 'admin');
insert into tbusuarios (iduser ,usuario ,login ,senha ,perfil)values(2,'Henrique Marega Jussani', 'hmjussani', '123', 'user');

create table tbclientes(
idcli VARCHAR(20) primary key,
nomecli varchar(100) not null,
contatocli varchar(50) not null,
endcli varchar(100) not null,
telcli varchar(20),
emailCli varchar(50)not null default 'contato@contato.com',
cidadeCli varchar(50)not null default 'Maringá',
estadoCli varchar(2)not null default 'PR'
);

create table tbpedido(
pedido VARCHAR(10) primary key not null,
data_os TIMESTAMP default current_timestamp,
equipamento varchar(100) not null,
defeito varchar(150) not null,
servico varchar(150),
tecnico varchar(20) ,
valor decimal(10,2),
idcli int not null,
compTipo varchar(100),
situacao varchar(30),
orcamento varchar(20),
foreign key (idcli) references tbclientes(idcli),
foreign key (compTipo) references tbcomponente(tipo),
);


create table tbcomponentes(
nserie varchar(20) primary key not null ,
cod VARCHAR(10) unique not null,
tipo VARCHAR(100)  not null,
modelo varchar(100) not null,
fabricante varchar(50) not null,
descricao varchar(200) not null,
custo decimal(10,2),
datavenda TIMESTAMP default current_timestamp

);

insert into tbcomponentes (nserie ,cod, tipo ,modelo ,fabricante ,descricao ,custo)values('xyz123456','001','Armazenamento', 'SSD',  'Samsung', 'SSD SATA', '190.00');

CREATE table tbproduto(
nserieProd varchar(20) primary key not null ,
tipoProd VARCHAR(100) not null,
modeloProd varchar(100) not null,
descricaoProd varchar(200) 

);


describe tbpedido;
describe tbusuarios;
describe tbclientes;
describe tbcomponentes;

