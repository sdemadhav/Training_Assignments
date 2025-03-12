import { useState } from "react";
import './styles.css';

function Counter() 
{
    const [count,setCount] = useState(0);


    function IncrCounter()
    {
        setCount(count+1);
        console.log(count);
    }

    function decrCounter()
    {
        setCount(count-1);
        console.log(count);
    }
    return (
        <div>
            <h1>Counter Demo</h1>
            <h2><span>{count}</span></h2>
            <button onClick={IncrCounter}>Increment</button>
            <button onClick={decrCounter}>Decrement</button>
        </div>
    )
}

export default Counter;