import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Menu from './components/Menu'
import {countCtx} from './state/context'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <countCtx.Provider value={{count, setCount}}>
      <Menu count={count}/>
    </countCtx.Provider>
    </>
  )
}

export default App
