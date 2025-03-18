import React, { useContext } from 'react'
import { countCtx } from '../state/context';

function CompB() {
    const obj = useContext(countCtx);

  return (
    <>
    <div>CompB </div>
    <h3>Using Count value from Context being passed from App : {obj.count}</h3>
    </>
  )
}

export default CompB