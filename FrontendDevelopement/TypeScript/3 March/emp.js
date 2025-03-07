// Typescript Does not mandate the class name and filename to be same
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var Emp = /** @class */ (function () {
    function Emp(name, age) {
        this.age = age;
        this.name = name;
    }
    Emp.prototype.display = function () {
        console.log("Name: " + this.name);
        console.log("Age: " + this.age);
    };
    return Emp;
}());
var Clerk = /** @class */ (function (_super) {
    __extends(Clerk, _super);
    function Clerk(name, age, salary, designation) {
        var _this = _super.call(this, name, age) || this;
        _this.salary = salary;
        _this.designation = designation;
        return _this;
    }
    Clerk.prototype.displayAll = function () {
        this.display();
        console.log("Salary: " + this.salary);
        console.log("Designation: " + this.designation);
    };
    return Clerk;
}(Emp));
var Programmer = /** @class */ (function (_super) {
    __extends(Programmer, _super);
    function Programmer(name, age, salary, designation) {
        var _this = _super.call(this, name, age) || this;
        _this.salary = salary;
        _this.designation = designation;
        return _this;
    }
    Programmer.prototype.displayAll = function () {
        this.display();
        console.log("Salary: " + this.salary);
        console.log("Designation: " + this.designation);
    };
    return Programmer;
}(Emp));
var A = /** @class */ (function () {
    function A() {
        console.log("A()");
    }
    return A;
}());
var B = /** @class */ (function (_super) {
    __extends(B, _super);
    function B() {
        var _this = _super.call(this) || this; //Super call is A must AND explicitly to be done when we have extended a class . In java it calls by default, not here.
        console.log("B()");
        return _this;
    }
    return B;
}(A));
console.log("-----------------------------------------------------");
var e1 = new Emp("Madhav", 21);
e1.display();
console.log("-----------------------------------------------------");
var e2 = new Clerk("karan", 23, 23000, "Clerk");
e2.displayAll();
console.log("-----------------------------------------------------");
var e2 = new Programmer("Sanat", 23, 23000, "Programmer");
e2.displayAll();
console.log("-----------------------------------------------------");
var b = new B();
