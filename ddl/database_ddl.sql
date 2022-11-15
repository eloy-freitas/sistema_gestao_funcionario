create table cargo (
	id_cargo serial primary key unique
	, nm_cargo varchar(50) not null unique
);

create table funcionario(
	id_funcionario serial primary key unique
	, nm_funcionario varchar(150) not null
	, vl_salario_base decimal not null
	, vl_distancia_trabalho decimal not null
	, vl_tempo_servico decimal
	, nu_idade int
	, dt_admissao date not null
	, dt_modificacao date not null
);

create table bonus(
	id_bonus serial primary key unique
	, nm_bonus varchar(100) not null unique
);

create table funcionario_bonus(
	id_bonus int not null
	, id_funcionario int not null
	, vl_bonus decimal not null
	, dt_modificacao date not null
	, constraint id_bonus_fk foreign key (id_bonus) references bonus(id_bonus)
	, constraint id_funcionario_bonus_fk foreign key (id_funcionario) references funcionario(id_funcionario)
);

create table funcionario_falta(
	id_funcionario int not null
	, qt_faltas int not null
	, dt_vigencia date
	, dt_modificacao date
	, constraint id_funcionario_falta_fk foreign key (id_funcionario) references funcionario(id_funcionario)
);

create table funcionario_cargo (
	id_cargo int not null
	, id_funcionario int not null
	, dt_modificacao date not null
	, constraint id_cargo_fk foreign key (id_cargo) references cargo(id_cargo)
	, constraint id_funcionario_cargo_fk foreign key (id_funcionario) references funcionario(id_funcionario)
);

create table salario(
	id_funcionario int not null
	, vl_salario_total decimal not null
	, dt_modificacao date
	, constraint id_funcionario_salario_fk foreign key (id_funcionario) references funcionario(id_funcionario)

);

alter sequence funcionario_id_funcionario_seq restart;
update funcionario  set id_funcionario=nextval('funcionario_id_funcionario_seq');

insert into bonus(nm_bonus) values
	('Bonûs distância do trabalho')
	, ('Bonûs por tempo de serviço')
	, ('Bonûs por assiduidade');

insert into cargo(nm_cargo)
values
    ('Contador')
	, ('Educador De Saúde')
	, ('Corretor De Negócios')
	, ('Ti De Suporte À Equipe')
	, ('Padeiro')
	, ('Especialista Em Saúde')
	, ('Caixa')
	, ('Supervisor De Logística Global')
	, ('Médico')
	, ('Designer')
	, ('Atendente')
	, ('Eletricista')
	, ('Tesoureiro')
	, ('Operador De Máquinas')
	, ('Corretor De Seguros')
	, ('Farmacêutico')
	, ('Técnico De Laboratório')
	, ('Banqueiro')
	, ('Representante Da Central De Chamadas')
	, ('Supervisor De Serviço')
	, ('Consultor De Investimentos')
	, ('Analista De Orçamento')
	, ('Enfermeira Ambulatorial')
	, ('Comprador Assistente')
	, ('Administrador De Sistemas')
	, ('Diretor-Executivo')
	, ('Oficial De Empréstimos')
	, ('Consultor De Pessoal')
	, ('Engenheiro De Design')
	, ('Fabricante')
	, ('Gestor De Projeto')
	, ('Gerente De Restaurante')
	, ('Paramédico')
	, ('Professor Associado')
	, ('Coordenador Da Recepção')
	, ('Pregoeiro Público')
	, ('Especialista Em Rh')
	, ('Dentista')
	, ('Trainee De Varejo')
	, ('Tecnólogo De Alimentos')
	, ('Gerente De Caixa')
	, ('Cozinhar')
	, ('Chef Manager')
	, ('Comissário De Bordo')
	, ('Auditor')
	, ('Coordenador De Rh')
	, ('Biólogo')
	, ('Pintor De Produção')
	, ('Desenvolvedor Da Web')
	, ('Conferencista')
	, ('Desenvolvedor Móvel')
	, ('Engenheiro De Software')
	, ('Inspetor')
	, ('Webmaster')
	, ('Corretor Da Bolsa')
	, ('Operador')
	, ('Audiologista')
	, ('Operador Cnc');