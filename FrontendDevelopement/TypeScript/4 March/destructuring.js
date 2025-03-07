let emp1 = {"name":"Madhav","age":35,"salary":60000,"designation":"Programmer"}

let emp2 = ["Karan",23,88000,"Jr. Developer"]

let {name,age,salary,designation="Tester"} = emp1;
let [a_name,a_age,a_salary,a_desigantion="Tester"] = emp2;

console.log("Name: "+name);
console.log("Age: "+age);
console.log("Salary: "+salary);
console.log("Designation: "+designation);

console.log("=====================================");

console.log("Name: "+a_name);
console.log("Age: "+a_age);
console.log("Salary: "+a_salary);
console.log("Designation: "+a_desigantion);

console.log("=====================================");


