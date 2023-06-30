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

drop table tbEquip;
create table tbEquip(
nserie varchar(20) PRIMARY KEY not null ,
model varchar(50) not null,
patEquip varchar(20) unique NOT NULL,
foreign key (model) references tbmodelo(model)
);

insert into tbEquipOS (idcli, nserie, idOrdServ, idModel, patProd)values("idcli","idOrdServ","id_pedido",1,"patrimonio");
select * from tbEquipOS;

create table tbOrdServ(
idOrdServ VARCHAR(10) PRIMARY KEY,
idcli VARCHAR(20) not null,
dataAbertura DATE NOT NULL,
dataFechamento DATE,
garantia bool DEFAULT FALSE,
defeito VARCHAR(100)DEFAULT "Verificar.",
solucao VARCHAR(100)DEFAULT "Verificar.",
aberta  boolean default true,
tecnico varchar(30)NOT NULL,
valor varchar(10),
foreign key (idcli) references tbclientes(idcli)
);

select * from tbOrdServ;

insert into tbordemServico (id_ordemServico, idcli, dataAbertura, garantia, defeito, tecnico, valor)values("12345","cliente","2023-06-28",FALSE,"Não liga","Tecnico","100,00");

drop table tbmodelo;

create table tbmodelo(
idModel int PRIMARY KEY AUTO_INCREMENT,
model varchar(50) unique not null ,
mem VARCHAR(50) not null,
mBoard VARCHAR(50) not null,
expansao varchar(50) default "Sem expansões",
armazenaTipo varchar(10)default "USB Flash",
armazenaModel varchar(50) not null,
fonteAlimenta VARCHAR(50)  not null,
sParalela varchar(5) default "0",
sSerial varchar(5) default "1",
redeLan varchar(50) default "onBoard",
wifi varchar(50) default "nao",
tipo varchar(50) default "ThinClient",
processador VARCHAR(50) not null,
gabinete varchar(50) not null
);



insert into tbmodelo(model, mem, mBoard, source, storage, sParalela,sSerial,redeLan, wifi, tipo, processador, gabinete) values("modelo","memoria","placaMae","fonteAlimenta","armazenamento","D25","DB9","lan","wifi","ThinClient", "CPU", "Gabinete");

describe tbpedido;
describe tbusuarios;
describe tbclientes;
describe tbcomponentes;


