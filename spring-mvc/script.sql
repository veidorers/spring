-- OneToMany

CREATE TABLE director(
                         id int generated by default as identity primary key ,
                         name varchar(100) not null unique ,
                         age int check(age > 10)
);
drop table director;
drop table movie;

create table movie(
                      id int generated by default as identity primary key ,
                      director_id int references director(id),
                      name varchar(200) unique not null,
                      year_of_production int check (year_of_production > 1900)
);

insert into director(name, age)
values
    ('Christopher Nolan', 50),
    ('Martin Scorsese', 78),
    ('Quentin Tarantino', 57),
    ('David Lynch', 70);

insert into movie(director_id, name, year_of_production)
values
    (1, 'Inception', 2003),
    (1, 'Interstellar', 2005),
    (2, 'Taxi Driver', 2007),
    (2, 'The Wolf of Wall Street', 2016),
    (3, 'Pulp Fiction', 2010),
    (3, 'Reservoir Dogs', 2018);

select * from director;
select * from movie;
select * from director join movie m on director.id = m.director_id;
select * from director left join movie m on director.id = m.director_id;

insert into movie(director_id, name, year_of_production) VALUES (55, 'best movie', 20000); --X


-- OneToOne

create table citizen(
                        id int generated by default as identity primary key ,
                        name varchar(100) not null ,
                        age int check ( age > 0 )
);

create table passport(
                         citizen_id int primary key references citizen(id),
                         passport_number int
);

INSERT INTO Citizen (name, age) VALUES ('Bob', 12);
INSERT INTO Citizen (name, age) VALUES ('Tom', 24);
INSERT INTO Citizen (name, age) VALUES ('Katy', 39);
INSERT INTO Citizen (name, age) VALUES ('Alice', 45);

INSERT INTO Passport (citizen_id, passport_number) VALUES (1, 12345);
INSERT INTO Passport (citizen_id, passport_number) VALUES (2, 75124);
INSERT INTO Passport (citizen_id, passport_number) VALUES (3, 91245);
INSERT INTO Passport (citizen_id, passport_number) VALUES (4, 19259);

select * from citizen;
select * from passport;

select * from citizen join passport p on citizen.id = p.citizen_id;
insert into citizen(name, age) values ('john', 1);
select * from citizen left join passport p on citizen.id = p.citizen_id;

select * from citizen cross join passport;

insert into passport(citizen_id, passport_number) values (1, 4326532); --X
insert into passport(citizen_id, passport_number) values (55, 4326532); --X


-- ManyToMany

create table movie(
                      id int generated by default as identity primary key ,
                      director_id int references director(id),
                      name varchar(200) unique not null,
                      year_of_production int check (year_of_production > 1900)
);

create table actor(
                      id int generated by default as identity primary key ,
                      name varchar(200),
                      age int check(age > 0)
);
drop table actor;

insert into movie(director_id, name, year_of_production)
values
    (1, 'Inception', 2003),
    (1, 'Interstellar', 2005),
    (2, 'Taxi Driver', 2007),
    (2, 'The Wolf of Wall Street', 2016),
    (3, 'Pulp Fiction', 2010),
    (3, 'Reservoir Dogs', 2018);

INSERT INTO Actor (name, age) VALUES ('Harvey Keitel', 81);
INSERT INTO Actor (name, age) VALUES ('Robert De Niro', 77);
INSERT INTO Actor (name, age) VALUES ('Leonardo DiCaprio', 46);
INSERT INTO Actor (name, age) VALUES ('Jason Statham', 53);
INSERT INTO Actor (name, age) VALUES ('Joe Pesci', 77);
INSERT INTO Actor (name, age) VALUES ('Samuel L. Jackson', 72);

create table actor_movie(
                            actor_id int references actor(id),
                            movie_id int references movie(id),
                            primary key (actor_id, movie_id)
);

drop table actor_movie;

INSERT INTO Actor_Movie VALUES (1, 1);
INSERT INTO Actor_Movie VALUES (1, 2);
INSERT INTO Actor_Movie VALUES (2, 5);
INSERT INTO Actor_Movie VALUES (2, 6);
INSERT INTO Actor_Movie VALUES(3, 4);
INSERT INTO Actor_Movie VALUES(5, 6);
INSERT INTO Actor_Movie VALUES (6, 2);
INSERT INTO Actor_Movie VALUES (6, 3);

INSERT INTO Actor_Movie VALUES (6, 3); --X

truncate table actor_movie;

select a.id, a.name, m.name, m.id from actor_movie am JOIN actor a on am.actor_id = a.id JOIN movie m on m.id = am.movie_id;