import axios from 'axios';
import React from 'react'
import {useState} from 'react'
function AddEmployees() {
    const [employees, setEmployees] = useState({"id": "", "name": "", "age": "", "salary": "", "department": ""});
    
    const assignData = (e)=>{
        setEmployees({...employees, [e.target.name]: e.target.value});
    }

    const insertEmployee = (e) => {
        e.preventDefault();
        axios.post('http://localhost:4000/employees', employees)
        .then((res) => {
            console.log(res.data);
        })
    }


  return (    
    <div>
        <h3>Please Enter Employee Details</h3>
        <input type="text" placeholder="Enter Emp Id" name="id" value={employees.id} onChange={assignData}></input>
        <input type="text" name="name" placeholder='Enter Emp Name' value={employees.name} onChange={assignData}></input>
        <input type="text" name="age" placeholder='Enter Emp Age' value={employees.age} onChange={assignData} ></input>
        <input type="text" name="salary" placeholder='Enter Emp Salary' value={employees.salary} onChange={assignData}></input>
        <input type="text" name="department" placeholder='Enter Emp Department' value={employees.department} onChange={assignData}></input>
        <button type="submit" onClick ={insertEmployee}>Submit</button>
    </div>

  )
}

export default AddEmployees