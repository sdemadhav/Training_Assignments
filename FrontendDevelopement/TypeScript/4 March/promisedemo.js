Promise promise = new Promoise(
				function(resolved,rejected)
				{
					if(s1+s2)=="WissenTechnology"
						resolved("Employee Object");
					else
						rejected();
				}
				);

promise
	.then(
		(val)=>
		{
			console.log("Best place to work 1");
			console.log(val);
			return "Staff Feedback";
		},
		()=>
		{
			console.log("There is some spelling mistake, pls check")
			throw new Error("O is missing");
		},
	)
	
	//Adding this to show we can have multiple then clauses for a promise
	.then(
		(val)=>
		{
			console.log("Best place to work 2");
			console.log(val);
			return "Staff Feedback";
		},
		()=>
		{
			console.log("There is some spelling mistake, pls check")
			throw new Error("O is missing");
		},
	)
	.catch(
		(err)=>
		{
			console.log("Errro handling invoked");
			consoole.log("Reason: "+err.message);
		}
	
	)