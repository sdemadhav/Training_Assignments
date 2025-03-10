function add(a,b)   //Traditional Function
{
	console.log("Addition: "+(a+b))
}

var sum = add //using inbuilt fucntions

add(10,10)
sum(10,10)

var sub = function(a,b)
{
	console.log("subtract: "+(a-b));
}

var mul = (a,b) => console.log("Multiply: "+(a*b))

var div = new Function("a","b","console.log('Dvision result -> '+(a/b))")

console.log(typeof(add))
console.log(typeof(sum))
console.log(typeof(sub))
console.log(typeof(mul))
console.log(typeof(div))

