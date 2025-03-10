class Emp
{
    name: string;
    age: number;
    readonly company: string = "Microsoft";

    constructor(name, age)
    {
        this.name = name;
        this.age = age;
    }

    display()
    {
        console.log(`Name: ${this.name}, Age: ${this.age}, Company: ${this.company}`);
    }
}

let e1 = new Emp("John", 30);
e1.display();