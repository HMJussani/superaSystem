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


create table tbInfoProd(
nserie varchar(20) not null ,
loteProd VARCHAR(10) unique not null,
modelo varchar(20) not null,
patProd varchar(20) not null,
foreign key (nserie) references tbproduto(nserie)
);

insert into tbcomponentes (nserie ,cod, tipo ,modelo ,fabricante ,descricao ,custo)values('xyz123456','001','Armazenamento', 'SSD',  'Samsung', 'SSD SATA', '190.00');

create table tbproduto(
nserie varchar(20) primary key not null ,
loteCompra varchar(10) unique not null,
patProd VARCHAR(20) unique not null,
model varchar(50)  unique not null,
mem VARCHAR(50) not null,
mBoard VARCHAR(50) not null,
source varchar(50) not null,
storage VARCHAR(50)  not null,
sParalela varchar(5) default "0",
sSerial varchar(5) default "1",
redeLan varchar(50) default "onBoard",
wifi varchar(50) default "off"


);

insert into tbproduto(nserie, loteCompra, patProd, model, mem, mBoard, storage, source, sParalela,sSerial,redeLan, wifi) values("12345","lote","patrimonio","modelo","memoria","placaMae","fonteAlimenta","armazenamento","D25","DB9","lan","wifi");

describe tbpedido;
describe tbusuarios;
describe tbclientes;
describe tbcomponentes;

