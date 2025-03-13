import {useEffect, useState} from 'react';
import axios from 'axios';
export default function GetEmployees(){

    const [employees, setEmployees] = useState([]);

    useEffect( () => {
        axios.get('http://localhost:4000/employees')
        .then((res) => {
            setEmployees(res.data);
            console.log(res.data);
        })
    },[employees])
    return (
        <>
        <h2>
            <h3>Employees Details</h3>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Salary</th>
                        <th>Department</th>
                    </tr>
                </thead>
                
                {employees.map((employee) => {
                    return (
                        <tbody key={employee.id}>
                        <tr>
                            <td>{employee.id}</td>
                            <td>{employee.name}</td>
                            <td>{employee.age}</td>
                            <td>{employee.salary}</td>
                            <td>{employee.department}</td>
                        </tr>
                        </tbody>
                    )
                })}
            </table>
        </h2>
        </>
    )
}