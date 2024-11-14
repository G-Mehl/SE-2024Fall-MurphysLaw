create database SE_Project;
use SE_Project;

create table Interests (
	id integer primary key,
    interests varchar(64) unique not null
);

create table Users (
	id integer primary key,
    full_name varchar(64),
    username varchar(64),
    email varchar(64),
    pass varchar(64),
    interests varchar(64),
    roleId varchar(10) unique
);

create table User_Interests (
	id integer,
    interest_id integer,
    primary key (id, interest_id),
    foreign key (id) references Users(id),
    foreign key (interest_id) references Interests(id)
);

create table Opportunities (
	id integer primary key,
    opportunities varchar(64),
    title varchar (64),
    descr text,
    location varchar(64),
    duration varchar(64),
    associated_interests integer unique,
    roleId varchar(10),
    foreign key (associated_interests) references Interests(id),
    foreign key (roleId) references Users (roleId)
);

insert into Interests (id, interests) values 
(0, 'Education & Literacy'),
(1, 'Environment & Conservation'),
(2, 'Animal Welfare'),
(3, 'Healthcare & Wellness'),
(4, 'Disaster Relief');

DELIMITER //

create procedure GetTags(in InterestsTag varchar(64))
BEGIN
	select o.*
    from Opportunities o
    join Interests i on o.associated_interests = i.id
    where i.interests = InterestsTag;
END //

DELIMITER ;
