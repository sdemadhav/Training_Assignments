import { Route, Routes } from 'react-router-dom';
import Home from './screens/Home';
import AboutUs from './screens/AboutUs';
import './styles.css'
import Contact from './screens/Contact';
import Netbanking from './screens/Netbanking';
import Services from './screens/Services'
import Welcome from './screens/Welcome';

function App() {
  return (
    <>
    <Routes>
      <Route path="/" element={<Home />}/>
      <Route path="/aboutUs" element={<AboutUs />} />
      <Route path="/contactUs" element={<Contact/>} />
      <Route path="/login" element={<Netbanking/>}/>
      <Route path="/services" element={<Services/>} />
      <Route path="/welcome" element={<Welcome/>} />
    </Routes>
    </>
  )
}

export default App
