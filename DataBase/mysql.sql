create database dbSupera;

use dbSupera;

create table tbusuarios(
iduser int primary key auto_increment,
usuario varchar(50) not null,
login varchar(20) not null unique,
senha varchar(20) not null,
perfil varchar(20) default 'Tecnico'

);

insert into tbusuarios (iduser ,usuario ,login ,senha ,perfil)values(1,'Administrador', 'admin', 'admin', 'admin');
insert into tbusuarios (iduser ,usuario ,login ,senha ,perfil)values(2,'Henrique Marega Jussani', 'hmjussani', '123', 'user');

create table tbclientes(
idcli VARCHAR(20) primary key,
nomecli varchar(100) not null,
contatocli varchar(50) not null,
endcli varchar(100) not null,
telcli varchar(20),
emailcli varchar(50)not null default 'contato@contato.com',
cidadecli varchar(50)not null default 'Maringá',
estadocli varchar(2)not null default 'PR'
);

insert into tbclientes (idcli , nomecli, contatocli, endcli , telcli ) values ("idcli" , "nomecli", "contatocli", "endcli" , "telcli");

create table tbpedido(
id_pedido VARCHAR(10) primary key,
data_os TIMESTAMP default current_timestamp,
qtde_equipamento int,
defeito varchar(150) not null,
servico varchar(150),
tecnico varchar(20) ,
valor decimal(10,2),
idcli VARCHAR(20) not null,
situacao varchar(30),
orcamento varchar(20),
foreign key (idcli) references tbclientes(idcli)

);


insert into tbpedido (id_pedido, qtde_equipamento,defeito ,servico ,tecnico ,valor,idcli ,situacao ,orcamento)values("id_pedido", 1,"defeito" ,"servico" ,"tecnico" ,10.0,"idcli" ,"situacao" ,"orcamento");

create table tbInfoProd(
nserie varchar(20) not null,
id_pedido VARCHAR(10),
model varchar(20) ,
patProd varchar(20) unique,
foreign key (id_pedido) references tbpedido(id_pedido),
foreign key (model) references tbproduto(model),
foreign key (nserie) references tbproduto(nserie)
);

insert into tbInfoProd (nserie, id_pedido, model, patProd)values("12345","id_pedido","modelo","patrimonio");

create table tbproduto(
nserie varchar(20) PRIMARY KEY not null ,
loteProd varchar(10),
patProd VARCHAR(20),
model varchar(50) unique not null,
mem VARCHAR(50) not null,
mBoard VARCHAR(50) not null,
source varchar(50) not null,
storage VARCHAR(50)  not null,
sParalela varchar(5) default "0",
sSerial varchar(5) default "1",
redeLan varchar(50) default "onBoard",
wifi varchar(50) default "nao"

);

insert into tbproduto(nserie, loteProd, patProd, model, mem, mBoard, storage, source, sParalela,sSerial,redeLan, wifi) values("12345","lote","patrimonio","modelo","memoria","placaMae","fonteAlimenta","armazenamento","D25","DB9","lan","wifi");

describe tbpedido;
describe tbusuarios;
describe tbclientes;
describe tbcomponentes;

