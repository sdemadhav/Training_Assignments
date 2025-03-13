import logo from './logo.svg';
import './App.css';
import GetEmployees from './GetEmployee';
import AddEmployees from './AddEmployees';

function App() {
  return (
    <div className="App">
      <h1>Employee Management App</h1>
      <hr></hr>
      <AddEmployees />
      <GetEmployees />
    </div>
  );
}

export default App;
