// a;
function abc()
{
    a = 10;
    var b = 20;
    console.log("from inside abc() -a : "+a);  
    console.log("from inside abc() -b : "+b);
}


console.log("from outside abc() -a: "+a);
console.log("from outside abc() -b: "+b);
abc();