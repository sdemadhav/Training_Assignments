var a = 10
var b = "10"

console.log("a==b is: "+(a==b))
console.log("a===b is "+(a===b))

var arr = [11,"hi",new Date(), 3.44 , false]

for(i=0; i<arr.length; i++)
{
	console.log(arr[i]);
} 

for(let x in arr)
{
	console.log(x+" : "+arr[x]);
}

console.log("----------------------");
for(let x of arr)
{
	console.log(x);
}