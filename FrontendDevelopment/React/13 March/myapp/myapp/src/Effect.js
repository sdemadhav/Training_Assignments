import { useState , useEffect } from "react";

function Effect()
{
    const [a, setA] = useState(0);
    const [b, setB] = useState(0);

    let updateA = () => {
        setA(a+1);
    }

    let updateB = () => {
        setB(b+1);
    }

    useEffect(() => {
        console.log("Component Mounted Successfully");
    },[]);
    return(

        <div>
            <h2>This is from Effect Component</h2>
            <h3>A: {a}</h3>
            <h3>B: {b}</h3>
            <button onClick={updateA}>Update A</button>
            <button onClick={updateB}>Update B</button>
        </div>
    );

}

export default Effect;