var a = 10
console.log(typeof(a))
console.log(isNaN(a))
var b = "2010.95"
console.log(parseInt(b))
console.log(parseFloat(b))
console.log(parseFloat(b)+10)
var c = "3+5-8*2"
console.log(eval(c))
var d = "2010.95ABC"
console.log(parseInt(d)+5)
console.log(parseFloat(d)+5)

var i = 1
function abc(){
    console.log("Hello: "+ i++)
}
function stop(){
    clearInterval(ref)
}

var ref = setInterval(abc, 1000)
setTimeout(stop, 15000)

console.log("I cant wait for any other function to complete")
console.log("I am not blocked by any function")


