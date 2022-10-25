

create table vigencia(
	id_vigencia serial primary key unique
	, dt_vigencia varchar(6) not null
	, dt_modificacao timestamp
);

create table cargo (
	id_cargo serial primary key unique
	, nm_cargo varchar(50) not null unique
);

create table funcionario(
	id_funcionario serial primary key unique
	, nm_funcionario varchar(150) not null
	, vl_salario_base decimal not null
	, vl_distancia_trabalho decimal not null
	, vl_salario_total decimal not null
	, nu_idade int not null
	, dt_admissao timestamp not null
	, id_Cargo int not null
	, dt_modificacao timestamp not null
	, constraint id_cargo_fk foreign key (id_cargo) references cargo(id_cargo)
);

create table bonus(
	id_bonus serial primary key unique
	, nm_bonus varchar(100) not null
	, dt_modificacao timestamp not null
);

create table funcionario_bonus(
	id_bonus int not null
	, id_funcionario int not null
	, vl_bonus decimal not null
	, dt_modificacao timestamp not null
	, constraint id_bonus_fk foreign key (id_bonus) references bonus(id_bonus)
	, constraint id_funcionario_bonus_fk foreign key (id_funcionario) references funcionario(id_funcionario)
);


create table falta_funcionario(
	id_vigencia int not null
	, id_funcionario int not null
	, qt_faltas int not null
	, dt_modificacao timestamp
	, constraint id_vigencia_fk foreign key (id_vigencia) references vigencia(id_vigencia)
	, constraint id_funcionario_falta_fk foreign key (id_funcionario) references funcionario(id_funcionario)
);


