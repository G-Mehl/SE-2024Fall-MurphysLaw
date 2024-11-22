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

CREATE TABLE Aggregated_Interests_Opportunities (
    interest_id INTEGER,
    interest_name VARCHAR(64),
    opportunity_count INTEGER,
    PRIMARY KEY (interest_id),
    FOREIGN KEY (interest_id) REFERENCES Interests(id)
);


insert into Interests (id, interests) values 
(0, 'Education & Literacy'),
(1, 'Environment & Conservation'),
(2, 'Animal Welfare'),
(3, 'Healthcare & Wellness'),
(4, 'Disaster Relief');

INSERT INTO Aggregated_Interests_Opportunities (interest_id, interest_name, opportunity_count)
SELECT 
    i.id AS interest_id,
    i.interests AS interest_name,
    COUNT(o.id) AS opportunity_count
FROM 
    Interests i
LEFT JOIN 
    Opportunities o ON i.id = o.associated_interests
GROUP BY 
    i.id, i.interests;


DELIMITER //

CREATE PROCEDURE UpdateAggregatedInterestsOpportunities()
BEGIN
    TRUNCATE TABLE Aggregated_Interests_Opportunities;

    INSERT INTO Aggregated_Interests_Opportunities (interest_id, interest_name, opportunity_count)
    SELECT 
        i.id AS interest_id,
        i.interests AS interest_name,
        COUNT(o.id) AS opportunity_count
    FROM 
        Interests i
    LEFT JOIN 
        Opportunities o ON i.id = o.associated_interests
    GROUP BY 
        i.id, i.interests;
END //

DELIMITER ;
