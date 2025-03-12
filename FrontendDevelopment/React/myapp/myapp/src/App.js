import Greet from "./Greet";
import JsxDemo from "./JsxDemo";
import Counter from "./Counter";
import Welcome from "./Welcome";


function App() {
  let a=10;
  let b = 20;

  return (
    <div>
      <h1>Hello From App Component</h1>
      <JsxDemo/>
      <br></br>
      <hr></hr>
      {(a>10)?<h2>Good Morning, everyone 'a' value is greater than 10</h2>:<h2>Good Evening everyone, 'a' value is less than 10</h2>}
      <br></br>
      <br></br>
      <Greet name="Madhav Jha" day="Morning" />
      <Greet name="Sanat Patil" day="Evening" />
      <Greet name="Karan Rajput LuckyNumberMan: 1812" day="Night" />
      <br></br>
      <Counter/>
      <br></br>
      <Welcome/>
    </div>
    
  );
}

export default App;