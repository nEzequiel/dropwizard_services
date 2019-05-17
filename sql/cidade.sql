Delete c,p from Comentario c inner join PontoTuristico p on p.id=c.pontoTuristico  where p.cidade=4


drop table Cidade;

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
    foreign key(cidade) references Cidade(id) on delete cascade
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


Create table Comentario(
    id int primary key auto_increment,
    usuario int,
    pontoTuristico int,
    dataAvaliacao Date,
    texto varchar(70) not null,
    foreign key(usuario) references Usuario(id) on delete cascade,
    foreign key(pontoTuristico) references PontoTuristico(id) on delete cascade
);

insert into Pais(nome) values('Brasil');
insert into Pais(nome) values('Estados Unidos');
insert into Pais(nome) values('Holanda');
insert into Pais(nome) values('Mexico');



insert into Estado(nome,pais) values('São Paulo',1);
insert into Estado(nome,pais) values('Amazonas',1);
insert into Estado(nome,pais) values('São Paulo',1);
insert into Estado(nome,pais) values('Bahia',1);
insert into Estado(nome,pais) values('Minas Gerais',1);
insert into Estado(nome,pais) values('Rio de Janeiro',1);
insert into Estado(nome,pais) values('Paraná',1);
insert into Estado(nome,pais) values('Pará',1);
insert into Estado(nome,pais) values('Colorado',2);
insert into Estado(nome,pais) values('Luisiana',2);
commit;

UPDATE PontoTuristico set nome='10', set cidade=1, set rua='da', set numero=3, set bairro='da', set cep=32 where id=1;

select c.id,c.nome,c.populacao, e.nome, p.nome from Cidade as c inner join Pais as p on p.id=c.pais inner join Estado as e on e.id=c.estado  where c.id > 0 and c.nome like 'a%'  limit 6