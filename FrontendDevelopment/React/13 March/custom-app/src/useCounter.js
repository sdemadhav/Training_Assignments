import { useState } from 'react';
function useCounter(val=0)
{
    const [count , setCount] = useState(val);
    
    function increment(){
        setCount(count+1);
    }

    function decrement(){
        setCount(count-1);
    }

    return [count , increment , decrement];
}

export default useCounter;