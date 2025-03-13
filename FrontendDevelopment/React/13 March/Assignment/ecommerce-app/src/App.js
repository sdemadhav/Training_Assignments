import logo from './logo.svg';
import './App.css';
import Products from './Products'
import Vendors from './Vendors'

function App() {
  return (
    <div className="App">
      <h1>Ecommerce-Management App</h1>
      <Products/>
      <Vendors/>
    </div>
  );
}

export default App;
