create table if not exists cargo (
	id_cargo serial primary key unique
	, nm_cargo varchar(50) not null unique
);

create table if not exists funcionario(
	id_funcionario serial primary key unique
	, nm_funcionario varchar(150) not null
	, vl_salario_base decimal not null
	, vl_distancia_trabalho decimal not null
	, vl_tempo_servico decimal
	, nu_idade int
	, dt_admissao date not null
	, dt_modificacao date not null
);

create table if not exists bonus(
	id_bonus serial primary key unique
	, nm_bonus varchar(100) not null unique
);

create table if not exists funcionario_bonus(
	id_bonus int not null
	, id_funcionario int not null
	, vl_bonus decimal not null
	, dt_modificacao date not null
	, constraint id_bonus_fk foreign key (id_bonus) references bonus(id_bonus)
	, constraint id_funcionario_bonus_fk foreign key (id_funcionario) references funcionario(id_funcionario)
);

create table if not exists funcionario_falta(
	id_funcionario int not null
	, qt_faltas int not null
	, dt_vigencia date
	, dt_modificacao date
	, constraint id_funcionario_falta_fk foreign key (id_funcionario) references funcionario(id_funcionario)
);

create table if not exists funcionario_cargo (
	id_cargo int not null
	, id_funcionario int not null
	, dt_modificacao date not null
	, constraint id_cargo_fk foreign key (id_cargo) references cargo(id_cargo)
	, constraint id_funcionario_cargo_fk foreign key (id_funcionario) references funcionario(id_funcionario)
);

create table if not exists salario(
	id_funcionario int not null
	, vl_salario_total decimal not null
	, dt_modificacao date
	, constraint id_funcionario_salario_fk foreign key (id_funcionario) references funcionario(id_funcionario)

);

alter sequence funcionario_id_funcionario_seq restart;
update funcionario  set id_funcionario=nextval('funcionario_id_funcionario_seq');

delete from funcionario_bonus;

delete from funcionario_cargo;

delete from funcionario_falta;

delete from salario;

delete from bonus;

delete from cargo;

delete from funcionario;

insert into bonus(nm_bonus) values
	('b??nus dist??ncia do trabalho')
	, ('b??nus por tempo de servi??o')
	, ('b??nus por assiduidade');

insert into cargo(nm_cargo)
values
    ('contador')
	, ('educador de sa??de')
	, ('corretor de neg??cios')
	, ('ti de suporte ?? equipe')
	, ('padeiro')
	, ('especialista em sa??de')
	, ('caixa')
	, ('supervisor de log??stica global')
	, ('m??dico')
	, ('designer')
	, ('atendente')
	, ('eletricista')
	, ('tesoureiro')
	, ('operador de m??quinas')
	, ('corretor de seguros')
	, ('farmac??utico')
	, ('t??cnico de laborat??rio')
	, ('banqueiro')
	, ('representante da central de chamadas')
	, ('supervisor de servi??o')
	, ('consultor de investimentos')
	, ('analista de or??amento')
	, ('enfermeira ambulatorial')
	, ('comprador assistente')
	, ('administrador de sistemas')
	, ('diretor-executivo')
	, ('oficial de empr??stimos')
	, ('consultor de pessoal')
	, ('engenheiro de design')
	, ('fabricante')
	, ('gestor de projeto')
	, ('gerente de restaurante')
	, ('param??dico')
	, ('professor associado')
	, ('coordenador da recep????o')
	, ('pregoeiro p??blico')
	, ('especialista em rh')
	, ('dentista')
	, ('trainee de varejo')
	, ('tecn??logo de alimentos')
	, ('gerente de caixa')
	, ('cozinhar')
	, ('chef manager')
	, ('comiss??rio de bordo')
	, ('auditor')
	, ('coordenador de rh')
	, ('bi??logo')
	, ('pintor de produ????o')
	, ('desenvolvedor da web')
	, ('conferencista')
	, ('desenvolvedor m??vel')
	, ('engenheiro de software')
	, ('inspetor')
	, ('webmaster')
	, ('corretor da bolsa')
	, ('operador')
	, ('audiologista')
	, ('operador cnc');