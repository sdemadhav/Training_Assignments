var increment=function incr()
{
	var a=0;
	var plus = function()
	{
		a=a+1;
	}	
	return plus;
}();

increment();
increment();
increment();