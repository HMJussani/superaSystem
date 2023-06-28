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


create table tbEquipOS(
nserie varchar(20) PRIMARY KEY not null ,
id_ordemServico VARCHAR(10) NOT NULL,
model varchar(20),
patProd varchar(20) unique NOT NULL,
foreign key (id_ordemServico) references tbordemServico(id_ordemServico),
foreign key (model) references tbmodelo(model)

);

insert into tbInfoProd (nserie, id_ordemServico, model, patProd)values("12345","id_pedido","modelo","patrimonio");


create table tbordemServico(
id_ordemServico VARCHAR(10) PRIMARY KEY,
idcli VARCHAR(20) not null,
dataAbertura DATE NOT NULL,
dataFechamento DATE,
garantia bool DEFAULT FALSE,
defeito VARCHAR(100)DEFAULT "Verificar.",
tecnico varchar(30)NOT NULL,
valor varchar(10),
foreign key (idcli) references tbclientes(idcli)
);

insert into tbordemServico (id_ordemServico, idcli, dataAbertura, garantia, defeito, tecnico, valor)values("12345","cliente","2023-06-28",FALSE,"Não liga","Tecnico","100,00");


create table tbmodelo(
model varchar(50) PRIMARY KEY,
mem VARCHAR(50) not null,
mBoard VARCHAR(50) not null,
processador VARCHAR(50) not null,
source varchar(50) not null,
storage VARCHAR(50)  not null,
sParalela varchar(5) default "0",
sSerial varchar(5) default "1",
redeLan varchar(50) default "onBoard",
wifi varchar(50) default "nao",
tipo varchar(50) default "ThinClient",
gabinete varchar(50) not null

);

insert into tbmodelo(tipo, processador, gabinete, model, mem, mBoard, storage, source, sParalela,sSerial,redeLan, wifi) values("12345","lote","patrimonio","modelo","memoria","placaMae","fonteAlimenta","armazenamento","D25","DB9","lan","wifi");

describe tbpedido;
describe tbusuarios;
describe tbclientes;
describe tbcomponentes;

