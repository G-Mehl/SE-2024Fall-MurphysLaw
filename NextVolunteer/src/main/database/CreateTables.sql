create database SE_Project;
use SE_Project;

create table Users (
	id integer,
    full_name varchar(64),
    username varchar(64),
    email varchar(64),
    password varchar(64),
    interests varchar(64),
    roleId varchar(10),
    primary key (id),
    foreign key (interests) references Interests (interests),
    foreign key (interests) references User_Interests (interests)
);

create table User_Interests (
	id integer,
    interests varchar(64),
    foreign key (interests) references Opportunities (associated_interests)
);

create table Interests (
	id integer,
    interests varchar(64)
);

create table Opportunities (
	id integer,
    opportunities varchar(64),
    title varchar (64),
    descr text,
    location varchar(64),
    duration varchar(64),
    associated_interests varchar(64),
    roleId varchar(10),
    foreign key (roleId) references Users (roleId)
);
