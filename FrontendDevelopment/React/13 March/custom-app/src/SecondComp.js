import React from 'react'
import useCounter from './useCounter'

export const SecondComp = () => 
{
    const [cnt,incr,decr] = useCounter();
    return (
        <div className="App">
        <div>Second</div>
        <span>Count : {cnt} </span><br></br>
        <button onClick={incr}>Increment</button>
        <button onClick={decr}>Decrement</button>
        </div>
    )
}
