Create table Pais(
    id int primary key auto_increment,
    nome varchar(50) not null
);

Create table Estado(
    id int primary key auto_increment,
    nome varchar(50) not null,
    pais int,
    foreign key(pais)references Pais(id)
);

Create table Cidade(
    id int primary key auto_increment, 
    nome varchar(50) not null,
    estado int,
    pais int,
    populacao bigint not null,
    foreign key(estado) references Estado(id),
    foreign key(pais)references Pais(id)
);

Create table PontoTuristico(
    id int primary key auto_increment,
    nome varchar(50) not null,
    cidade int,
    rua varchar(50) not null,
    numero int not null,
    bairro varchar(50),
    cep int not null,
    abertura Time not null,
    fechamento Time not null,
    foreign key(cidade) references Cidade(id)
);

Create table Usuario(
    id int primary key auto_increment,
    nome varchar(50) not null,
    sobrenome varchar(50) not null,
    email varchar(50) not null,
    telefone varchar(50) not null,
    login varchar(40) not null,
    senha varchar(40) not null
);

select *from Comentario where pontoTuristico=2;
Create table Avaliacao(
    id int primary key auto_increment,
    usuario int,
    pontoTuristico int,
    dataAvaliacao Date,
    valor int not null,
    foreign key(usuario) references Usuario(id),
    foreign key(pontoTuristico) references PontoTuristico(id)
);

Create table Comentario(
    id int primary key auto_increment,
    usuario int,
    pontoTuristico int,
    dataAvaliacao Date,
    texto varchar(70) not null,
    foreign key(usuario) references Usuario(id),
    foreign key(pontoTuristico) references PontoTuristico(id)
);

insert into Pais(nome) values('Brasil');
insert into Pais(nome) values('Estados Unidos');
insert into Pais(nome) values('Holanda');
insert into Pais(nome) values('Mexico');



insert into Estado(nome,pais) values('São Paulo',1);
insert into Estado(nome,pais) values('Amazonas',1);
insert into Estado(nome,pais) values('Colorado',2);
insert into Estado(nome,pais) values('Luisiana',2);
commit;

UPDATE PontoTuristico set nome='10', set cidade=1, set rua='da', set numero=3, set bairro='da', set cep=32 where id=1;