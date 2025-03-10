function add (...a)
{
    let sum = 0;
    for (let i of a)
    {
        sum += i;
    }
    console.log("Result is : "+sum);
}

add(1,2,3,4,5,6,7,8,9,10); 
add(1,2,3);
add()

console.log

var emp = ["Rajesh", 25, 30000, "Tester"];

function display(name, age, salary, designation) 
{
    console.log("Name: "+name);
    console.log("Age: "+age);
    console.log("Salary: "+salary);
    console.log("Designation: "+designation);
    console.log("==================================");
}
display(...emp);