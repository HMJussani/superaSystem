create database dbSupera;

use dbSupera;

create table tbusuarios(
iduser int primary key auto_increment,
usuario varchar(50) not null,
login varchar(20) not null unique,
senha varchar(20) not null,
perfil varchar(20) default 'Tecnico'

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


create table tbInfoProd(
nserie varchar(20) primary key not null,
loteProd VARCHAR(10) unique not null,
model varchar(20) unique not null,
patProd varchar(20) unique not null

);

insert into tbInfoProd (nserie, loteProd, model, patProd)values("12345","lote","modelo","patrimonio");

create table tbproduto(
nserie varchar(20) not null ,
loteProd varchar(10)  not null,
patProd VARCHAR(20)  not null,
model varchar(50)   not null,
mem VARCHAR(50) not null,
mBoard VARCHAR(50) not null,
source varchar(50) not null,
storage VARCHAR(50)  not null,
sParalela varchar(5) default "0",
sSerial varchar(5) default "1",
redeLan varchar(50) default "onBoard",
wifi varchar(50) default "off",
foreign key (nserie) references tbInfoProd(nserie),
foreign key (loteProd) references tbInfoProd(loteProd),
foreign key (model) references tbInfoProd(model),
foreign key (patProd) references tbInfoProd(patProd)


);

insert into tbproduto(nserie, loteProd, patProd, model, mem, mBoard, storage, source, sParalela,sSerial,redeLan, wifi) values("12345","lote","patrimonio","modelo","memoria","placaMae","fonteAlimenta","armazenamento","D25","DB9","lan","wifi");

describe tbpedido;
describe tbusuarios;
describe tbclientes;
describe tbcomponentes;

