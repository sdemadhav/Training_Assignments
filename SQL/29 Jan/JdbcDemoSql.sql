create table EMPLOYEE(name varchar(20),age int);
drop table EMPLOYEE;

-- Delete all records keep the table
delete from EMPLOYEE;
select * from EMPLOYEE;

create or replace procedure dummy_records()
language plpgsql
as $$
begin
	insert into EMP values ('Guest',21);
end;
$$

