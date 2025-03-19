import Home from './Home/Home'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Netbanking from './Netbanking/Netbanking'
import Navbar from './components/Navbar'
import Welcome from './Welcome/Welcome'
import MoneyTransfer from './MoneyTransfer/MoneyTransfer'

function App() {
  return (
    <BrowserRouter>
    <Navbar/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/register" element={<Netbanking />} />
        <Route path="/login" element={<Netbanking/>} />
        <Route path="/welcome" element={<Welcome/> } />
        <Route path="/transfer" element={<MoneyTransfer/> } />
      </Routes>
    </BrowserRouter>
  )
}

export default App

