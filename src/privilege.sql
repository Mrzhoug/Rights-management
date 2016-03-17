create database day20;

use day20;

# �û� users
create table users
(
	id varchar(40) primary key,
	username varchar(20),
	password varchar(20)
);

# ��ɫ roles
create table roles
(
	id varchar(40) primary key,
	name varchar(20),
	description varchar(60)
);

# ��Դ resource
create table resources
(
	id varchar(40) primary key,
	name varchar(20),
	url varchar(100)
);

# �м�� users_roles ����users �� roles ֮��Ķ�Զ��ϵ
create table users_roles
(
	users_id varchar(40),
	roles_id varchar(40),
	primary key(users_id, roles_id),
	constraint users_id_FK foreign key(users_id) references users(id),
	constraint roles_id_FK foreign key(roles_id) references roles(id)
);

# �м�� roles_resources ���� roles �� resources �Ķ�Զ��ϵ
create table roles_resources
(
	resources_id varchar(40),
	roles_id varchar(40),
	primary key(resources_id, roles_id),
	constraint resources_id_FK foreign key(resources_id) references resources(id),
	constraint roles_id_FK1 foreign key(roles_id) references roles(id)
)