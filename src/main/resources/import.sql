-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into person (id, name, password) values(100, 'admin', 'admin');
insert into person (id, name, password) values(101, 'bob', '0000');
insert into person (id, name, password) values(102, 'tom', '1111');
insert into grade (id, idPerson, coursValue, grade) values(1, 101, 1, 10.0);
insert into grade (id, idPerson, coursValue, grade) values(2, 101, 2, 14.0);
insert into grade (id, idPerson, coursValue, grade) values(3, 102, 2, 13.0);
