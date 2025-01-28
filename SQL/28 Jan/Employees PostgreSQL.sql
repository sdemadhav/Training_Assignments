select * from employees;
 
drop table employees;
 
CREATE TYPE designation_enum AS ENUM ('Programmer', 'Tester', 'Admin', 'Manager');
 
 
CREATE TABLE employees(
	eid SERIAL PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	age SMALLINT,
	gender VARCHAR(15) NOT NULL,
	salary NUMERIC(8, 2) DEFAULT 15000.00,
	designation designation_enum NOT NULL,
	email_id VARCHAR(255) NOT NULL,
	married BOOLEAN NOT NULL,
	joining_date DATE DEFAULT CURRENT_DATE,
	manager_id int,
	CONSTRAINT fk_manager FOREIGN KEY (manager_id) REFERENCES employees (eid),
	CONSTRAINT check_email_id CHECK(email_id ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
	CONSTRAINT check_age CHECK(age>=21 AND age<=60),
	CONSTRAINT check_gender CHECK(gender IN ('Male', 'Female'))
);
 
INSERT INTO employees (name, age, gender, salary, designation, email_id, married, joining_date)
VALUES ('Alice Johnson', 35, 'Female', 25000.00, 'Manager', 'alice.johnson@example.com', TRUE, '2025-01-01');
 
INSERT INTO employees (name, age, gender, salary, designation, email_id, married, joining_date, manager_id)
VALUES ('Bob Smith', 28, 'Male', 20000.00, 'Tester', 'bob.smith@example.com', FALSE, '2025-01-10', 1);
 
SELECT * FROM employees;
 
INSERT INTO employees (name, age, gender, salary, designation, email_id, married, joining_date, manager_id)
VALUES
	('Bob Smith', 28, 'Male', 25000.00, 'Programmer', 'bob.smith@example.com', FALSE, CURRENT_DATE, 1),
    ('Charlie Brown', 32, 'Male', 22000.00, 'Tester', 'charlie.brown@example.com', TRUE, CURRENT_DATE, 1),
    ('Diana White', 30, 'Female', 26000.00, 'Programmer', 'diana.white@example.com', FALSE, CURRENT_DATE, 1),
    -- Admin Reporting to Alice
    ('Eve Green', 35, 'Female', 28000.00, 'Admin', 'eve.green@example.com', TRUE, CURRENT_DATE, 1),
    -- New Manager (No Manager Reference)
    ('Frank Black', 45, 'Male', 35000.00, 'Manager', 'frank.black@example.com', TRUE, CURRENT_DATE, NULL),
    -- Employees Reporting to Frank (Manager with eid = 6)
    ('Grace Adams', 27, 'Female', 24000.00, 'Tester', 'grace.adams@example.com', FALSE, CURRENT_DATE, 6),
    ('Henry Ford', 29, 'Male', 23000.00, 'Programmer', 'henry.ford@example.com', FALSE, CURRENT_DATE, 6),
    ('Ivy Nelson', 31, 'Female', 27000.00, 'Admin', 'ivy.nelson@example.com', TRUE, CURRENT_DATE, 6),
    -- Another Tester Reporting to Bob (eid = 2)
    ('Jack Brown', 26, 'Male', 21000.00, 'Tester', 'jack.brown@example.com', FALSE, CURRENT_DATE, 2),
	('Jackie Chan', 49, 'Male', 52000.00, 'Programmer', 'jackie.chan@example.com', FALSE, CURRENT_DATE, 1);
 
 
select * from employees where age between 30 and 35;
select * from employees where name like '%a';
select * from employees where name like 'a%';
select * from employees where name like '%a%';
 
select * from employees limit 5; -- will get me only 5 records, this is limiting(or for pagination kind of emulation)
select * from employees limit 5 offset 3; -- skip first 3
select * from employees offset 3 limit 5; -- offset before limit also fine
 
 
-- String functions
select upper(name), age from employees;
select reverse('Madam') = 'Madam';
 
select reverse('MadaM');
 
select current_date;
 
create table role (designation varchar(20) primary key, max_limit integer, min_salary integer );
 
insert into role values
('Tester', 100, 20000),
('Programmer', 200, 30000),
('Manager', 15, 120000),
('Clerk', 50, 25000);
 
SELECT * FROM employees
	INNER JOIN 
ROLE ON DESIGNATION 
	WHERE EMP.salary !=ROLE.min_salry;

-- Fine empoloyees having salary greater than their respective managers, Method 1
Select e1.name as emp_name, e1.salary as emp_salary, e2.name as manager_name, e2.salary as manager_salary
From employees e1
Join employees e2 on e1.manager_id = e2.eid
where e1.salary > e2.salary;

-- Fine empoloyees having salary greater than their respective managers, Method 2
SELECT 
    e1.name AS emp_name,
    e1.salary AS emp_salary,
    e2.name AS manager_name,
    e2.salary AS manager_salary
FROM 
    employees e1
LEFT JOIN 
    employees e2 
ON 
    e1.manager_id = e2.eid
WHERE 
    e1.salary > e2.salary;
	
-- Roll UP Function(Specific to PostgreesSQl)
select coalesce(Designation, 'Total') as designation , sum(salary)
from employees
group by rollup(Designation)
order by sum(salary);


-- EMPLOYEE
	--EID (Primary Key)
	--NAME
	--AGE (21 to 60)
	--GENDER (MALE/FEMALE)
	--SALARY (999999.99)
	--DESIGNATION (PROGRAMMER/TESTER/ADMIN/MANAGER)
	--MGR_ID (Pointing to EID)
	--EMAIL_ID (abc@xyz.com)
	--MARRIED (true/false)
	--JOINING_DATE (current_date by default)