import Home from './Home/Home'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Netbanking from './Netbanking/Netbanking'
import Navbar from './components/Navbar'
import Welcome from './Welcome/Welcome'
import MoneyTransfer from './MoneyTransfer/MoneyTransfer'
import RegisterCustomer from './Netbanking/RegisterCustomer'
import { Provider } from 'react-redux'
import { store } from './ReduxContainer/AuthContainer/store'
import MiniStatement from './MiniStatement/MiniStatement'

function App() {
  return (
    
    <BrowserRouter>
    <Provider store={store}>
    <Navbar/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/register" element={<RegisterCustomer />} />
        <Route path="/login" element={<Netbanking/>} />
        <Route path="/welcome" element={<Welcome/> } />
        <Route path="/transfer" element={<MoneyTransfer/> } />
        <Route path='/statement' element={<MiniStatement/>} />
        <Route path="*" element={<h1>Page not found</h1>} />
      </Routes>
      </Provider>
    </BrowserRouter>
    
  )
}

export default App

