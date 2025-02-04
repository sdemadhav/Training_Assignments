CREATE TABLE EMP_Manage_App (
    eid SERIAL PRIMARY KEY,
    e_name VARCHAR(20) NOT NULL,
    e_age INT,
    e_salary int,
    e_designation VARCHAR(20),
    e_department VARCHAR(20)
);


drop table EMP_Manage_App;

-- Delete all records keep the table
delete from EMP_Manage_App;
select * from EMP_Manage_App;
