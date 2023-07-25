create database dbSupera;

use dbSupera;

create table tbusuarios(
iduser int primary key auto_increment,
usuario varchar(50) not null,
login varchar(50) not null unique,
senha varchar(50) not null,
perfil varchar(20) default 'Tecnico',
email varchar(50)not null default 'contato@contato.com'
);

#alter table tbusuarios add email varchar(50)not null default 'contato@contato.com';


insert into tbusuarios (iduser ,usuario ,login ,senha ,perfil)values(1,'Administrador', 'admin', 'admin', 'admin');
#insert into tbusuarios (iduser ,usuario ,login ,senha ,perfil)values(2,'Henrique Marega Jussani', 'hmjussani', '123', 'user');

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

#insert into tbclientes (idcli , nomecli, contatocli, endcli , telcli ) values ("idcli" , "nomecli", "contatocli", "endcli" , "telcli");

create table tbmodelo(
model varchar(50) PRIMARY KEY  ,
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
gabinete varchar(50) not null,
obsoleto  boolean default false
);

alter table tbmodelo add obsoleto boolean default false;

create table tbOrdServ(
idOrdServ VARCHAR(10) PRIMARY KEY,
idcli VARCHAR(20) not null,
dataAbertura DATE NOT NULL,
dataFechamento DATE,
defeito VARCHAR(100)DEFAULT "Verificar.",
solucao VARCHAR(100)DEFAULT "Verificar.",
aberta  boolean default true,
tecnico varchar(30)NOT NULL,
valor varchar(10),
foreign key (idcli) references tbclientes(idcli)
);

#drop table tbEquip;
create table tbEquip(
nserie varchar(20) PRIMARY KEY,
idOrdServ VARCHAR(10)not null,
model varchar(50) not null,
patEquip varchar(20) NOT NULL,
idcli VARCHAR(20) not null,
garantia bool DEFAULT FALSE,
analizado bool default false,
foreign key (model) references tbmodelo(model),
foreign key (idOrdServ) references tbOrdServ(idOrdServ),
foreign key (idcli) references tbclientes(idcli)
);

alter table tbEquip add analizado bool default false;

update tbEquip set analizado = false;
select * from tbEquip;

create table tbdefsol(
idDefeito int primary key auto_increment,
nserie varchar(20) ,
defeito VARCHAR(100)DEFAULT "Verificar.",
solucao VARCHAR(100)DEFAULT "Verificar.",
foreign key (nserie) references tbEquip(nserie)
);

#alter table tbEquip add garantia bool DEFAULT FALSE;

#insert into tbEquip(nserie, idOrdServ, model, patEquip, idcli) values("nserie","3072023", "modelo", "patrimonio", "idcli");
#select * from tbEquip;

#select * from tbOrdServ;

#select idOrdServ, dataAbertura, dataFechamento, aberta, nomecli from tbOrdServ join tbclientes on tbOrdServ.idcli = tbclientes.idcli where idOrdServ ="3072023";

#insert into tbordemServico (id_ordemServico, idcli, dataAbertura, garantia, defeito, tecnico, valor)values("12345","cliente","2023-06-28",FALSE,"Não liga","Tecnico","100,00");

#drop table tbmodelo;

#insert into tbmodelo(model, mem, mBoard, source, storage, sParalela,sSerial,redeLan, wifi, tipo, processador, gabinete) values("modelo","memoria","placaMae","fonteAlimenta","armazenamento","D25","DB9","lan","wifi","ThinClient", "CPU", "Gabinete");

#update tbdefsol set defeito="teste", solucao="teste2" where nserie="30909";

select * from tbEquip where nserie="17763";
update tbEquip set idOrdServ = "27905" where nserie = "8823";

insert into tbdefsol (nserie, defeito, solucao)values("30909","Defeito","Solucao");

#drop table tbdefsol;
#describe tbpedido;
#describe tbusuarios;
#describe tbclientes;
#describe tbcomponentes;
#describe tbEquip;
select * from tbEquip;
#delete from tbEquip where nserie=?;
#select * from tbmodelo;