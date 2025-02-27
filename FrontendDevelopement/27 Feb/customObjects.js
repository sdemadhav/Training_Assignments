function display(obj){
    console.log("Name: "+obj.name);
    console.log("Age: "+obj.age);
    console.log("Salary: "+obj.salary);
    console.log("Designation: "+obj.designation);
    console.log("==================================");
}

console.log("Constructor Based Object Creation\n");
var emp1 = new Object();
emp1.name = "Raju"
emp1.age = 25;
emp1.salary=30000
emp1.designation="Tester";

display(emp1);

console.log("Function Based Object Creation\n");
function Emp(name, age, salary, designation){
    this.name = name;
    this.age = age;
    this.salary = salary;
    this.designation = designation;
}

var emp2 = new Emp("Ravi", 30, 40000, "Developer");
var emp3 = new Emp("Rahul", 35, 50000, "Manager");

display(emp2);
display(emp3);

console.log("JSON Based Object Creation\n");

var emp4 = {"name":"Ravi", "age":30, "salary":40000, "designation":"Developer"};
display(emp4);

console.log("Class Based Object Creation\n");
class Employee{
    constructor(name, age, salary, designation){
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
    }
}

var emp5 = new Employee("Ravi", 30, 40000, "Developer");
var emp6 = new Employee("Rahul", 35, 50000, "Manager");
display(emp5);
display(emp6);

console.log("==================================");

console.log("Type of emp1: "+typeof emp1);
console.log("Type of emp2: "+typeof emp2);
console.log("Type of emp3: "+typeof emp3);
console.log("Type of emp4: "+typeof emp4);
console.log("Type of emp5: "+typeof emp5);
console.log("Type of emp6: "+typeof emp6);
