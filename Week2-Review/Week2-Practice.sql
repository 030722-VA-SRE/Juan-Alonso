
create table if not exists employees(
id serial primary key,
first_name varchar(20) not null,
last_name varchar(20) not null,
salary integer not null,
dept varchar(25)
);

insert into employees(first_name, last_name,salary,dept) values 
	('Michael', 'Scott', 65,'Sales'),
	('Dwight', 'Schrute', 75,'Sales'),
	('Toby', 'Flenderson', 80,'HR'),
	('Jim', 'Halpert', 90,'Sales'),
	('Oscar', 'Martinez', 90,'Accounting'),	
	('Angela', 'Martin', 75,'Accounting'),	
	('Kevin', 'Malone', 70,'Accounting'),	
	('Holly', 'Flax', 60,'HR'),
	('Creed', 'Branton', 70,'Quality Assurance');	
	

--Write a query to find all data in the table
	select * from employees;

--Write a query to find employees with a salary over 75
	select * from employees where salary > 75;

--Write a query to find employees whose first name contains an 'e' or whose last name begins with 'S'\
	select * from employees where first_name like '%e%'
	union 
	select * from employees where last_name like 'S%';

--Write a query to find the first name of all employees who do not work in accounting
	select first_name from employees where dept != 'Accounting'; 

--Write a query to find the average salary of all employees whose last names begin with 'M'
	select AVG(salary), last_name from employees where last_name like 'M%' group by last_name;

--Write a query to find the highest paid salesperson
	select * from employees where salary = (select max(salary) from employees);

--Write a query to combine the resultsets of any two previous queries
	select * from employees
	union all
	select * from employees where salary > 75;


--Remove all members of accounting from the database
	delete * from employees where dept = 'Accounting';

--Given removing the dept column from the employees table and creating a table 'department' and linking the two via a foreign key relationship

	alter table employees drop column dept;
	alter table employees add column dept_id integer;
	
	update employees set dept_id = 1 where id = 1;
	update employees set dept_id = 1 where id = 2;
	update employees set dept_id = 2 where id = 3;
	update employees set dept_id = 1 where id = 4;
	update employees set dept_id = 3 where id = 5;
	update employees set dept_id = 3 where id = 6;
	update employees set dept_id = 3 where id = 7;
	update employees set dept_id = 2 where id = 8;
	update employees set dept_id = 4 where id = 9;


	create table if not exists department (
	dept_id serial primary key unique,
	department_name varchar(25) unique,
	foreign key (dept_id) references employees
	); 

	insert into department (department_name) values 
	('Sales'),
	('HR'),
	('Accounting'),
	('Customer Service');


--Write a query to find the salary of the lowest paid salesperson (HINT: use a join) 	

select min(salary) from employees join department 
on employees.id = department.dept_id;  

--Write a query to find the average salary of each department
select avg(salary), department_name from employees join department
on employees.dept_id = department.dept_id
group by department_name; 

--Write a query to find all possible combinations of employees and departments. How many records do you expect?
select employees.first_name, department.department_name from 
employees
cross join 
department;
	--36 combinations -> 9 employees * 4 departments 

--Change the name of department 4 to 'Quality Assurance'
	update department 
	set department_name = 'Quality Assurance' 
	where department_name = 'Customer Service';

--Remove both tables
	drop table employees;
	drop table department;
