import React from 'react'
import useCounter from './useCounter'

export const FirstComp = () => 
{
    const [cnt,incr,decr] = useCounter(5);
    return (
        <div className="App">
        <div>FirstComp</div>
        <span>Count : {cnt} </span><br></br>
        <button onClick={incr}>Increment</button>
        <button onClick={decr}>Decrement</button>
        </div>
    )
}
