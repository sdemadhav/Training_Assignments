import logo from './logo.svg';
import './App.css';
import BookContainer from './components/BookContainer';
import { Provider } from 'react-redux';
import store from './reduxContainer/Store';

function App() {
  return (
    <div className="App">
      <Provider store={store}>
      <div>
        <h1>React redux App</h1>
        <BookContainer/>
      </div>
      </Provider>
    </div>
  );
}

export default App;
