import { useState,useRef } from "react";
import React from 'react';

function Welcome()
{
    const [name,setName] = useState("");

    const refElement = useRef("");
    function clearField()
    {
        setName("");
        refElement.current.focus();
        refElement.current.style.color = "blue";
        refElement.current.value = "Subramaniyam";
    }
    return(
        <>
        <hr></hr>
        <h1>Welcome to the React World {name}</h1>
        <h3></h3>
            <input ref={refElement} type="text" value={name} onChange={(e) => setName(e.target.value)} />
            <input type="reset" value="CLEAR" onClick={clearField} />
        </>
    )
}

export default Welcome;