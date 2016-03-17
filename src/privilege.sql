create database day20;

use day20;

# 用户 users
create table users
(
	id varchar(40) primary key,
	username varchar(20),
	password varchar(20)
);

# 角色 roles
create table roles
(
	id varchar(40) primary key,
	name varchar(20),
	description varchar(60)
);

# 资源 resource
create table resources
(
	id varchar(40) primary key,
	name varchar(20),
	url varchar(100)
);

# 中间表 users_roles 描述users 和 roles 之间的多对多关系
create table users_roles
(
	users_id varchar(40),
	roles_id varchar(40),
	primary key(users_id, roles_id),
	constraint users_id_FK foreign key(users_id) references users(id),
	constraint roles_id_FK foreign key(roles_id) references roles(id)
);

# 中间表 roles_resources 描述 roles 和 resources 的多对多关系
create table roles_resources
(
	resources_id varchar(40),
	roles_id varchar(40),
	primary key(resources_id, roles_id),
	constraint resources_id_FK foreign key(resources_id) references resources(id),
	constraint roles_id_FK1 foreign key(roles_id) references roles(id)
)