// Typescript Does not mandate the class name and filename to be same

class Emp
{
	name:String
	age:number
	
	constructor(name:String,age:number)
	{
		this.age = age;
		this.name =name;
	}
	
	display()
	{
		console.log("Name: "+this.name);	
		console.log("Age: "+this.age);
	}
}

class Clerk extends Emp
{	
	salary:number;
	designation:string;
	
	constructor(name:string,age:number,salary:number,designation:string)
	{
		super(name,age);
		this.salary = salary;
		this.designation = designation;
	}
	displayAll()
	{
		this.display();
		console.log("Salary: "+this.salary);
		console.log("Designation: "+this.designation);
	}
}

class Programmer extends Emp
{	
	salary:number;
	designation:string;
	
	constructor(name:string,age:number,salary:number,designation:string)
	{
		super(name,age);
		this.salary = salary;
		this.designation = designation;
	}
	displayAll()
	{
		this.display();
		console.log("Salary: "+this.salary);
		console.log("Designation: "+this.designation);
	}
}

class A
{
	constructor()
	{
		console.log("A() is called");
	}
	
}

class B extends A
{
	constructor()
	{
	super(); //Super call is A must AND explicitly to be done when we have extended a class . In java it calls by default, not here.
	console.log("B() is called");
	}
}


console.log("-----------------------------------------------------");
var e1 = new Emp("Madhav",21)
e1.display();

console.log("-----------------------------------------------------");
var e2 = new Clerk("karan",23,23000,"Clerk");
e2.displayAll();

console.log("-----------------------------------------------------");
var e2 = new Programmer("Sanat",23,23000,"Programmer");
e2.displayAll();

console.log("-----------------------------------------------------");

var b = new B();
