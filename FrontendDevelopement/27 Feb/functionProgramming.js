var a = 10;

a = function(){console.log("HELLO")}
console.log(a)
a()

console.log("-----------------------------")

function abc(){
	console.log("From function abc()...");
}

function xyz() {
	console.log("From function xyz()...")
}

function demo1(op){
	console.log("<<<<< from function demo1() >>>>>")
}

function demo2()
{
	console.log("<<<<< From function demo2() >>>>>")
	return xyz;
}

demo1(abc)
demo1(xyz)

console.log("-----------------------------")

var res1 = demo2();
var res2 = demo1();
res2

console.log("-----------------------------")

demo2()
demo2()()

