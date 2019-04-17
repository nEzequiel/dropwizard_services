Create table Cidade(
id int primary key generated always as identity
        (start with 1, increment by 1), 
nome varchar(50) not null,
estado int,
pais int,
populacao bigint not null,
foreign key(estado) references Estado(id),
foreign key(pais)references Pais(id)
);


Create table Estado(
    id int primary key generated always as identity
        (start with 1, increment by 1), 
    nome varchar(50) not null,
    pais int,
    foreign key(pais)references Pais(id)
);

Create table Pais(
    id int primary key generated always as identity
    (start with 1, increment by 1) ,
    nome varchar(50) not null
);


insert into Pais(nome) values('Brasil');
insert into Pais(nome) values('Estados Unidos');
insert into Pais(nome) values('Holanda');
insert into Pais(nome) values('Mexico');

select*from Pais;

insert into Estado(nome,pais) values('São Paulo',1);
insert into Estado(nome,pais) values('Amazonas',1);
insert into Estado(nome,pais) values('Colorado',2);
insert into Estado(nome,pais) values('Luisiana',2);
