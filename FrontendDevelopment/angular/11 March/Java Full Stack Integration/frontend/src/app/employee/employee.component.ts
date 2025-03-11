import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { EmployeeService } from '../shared/employee.service';
import { Employee } from '../shared/employee.model';

@Component({
  selector: 'app-employee',
  standalone: false,
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css',
  providers: [EmployeeService]
})
export class EmployeeComponent implements OnInit {

  update:boolean = false;
  ngOnInit(): void {
    this.resetForm();
  }
  constructor(public es:EmployeeService) { }

  onSubmit(form:NgForm)
  {
    if(!this.update)
      {
        this.es.postEmployee(form.value).subscribe((res)=>{
          this.resetForm(form);
          console.log("Employee added successfully");
        });
      }
      else{
        this.es.putEmployee(form.value).subscribe((res)=>{
          this.resetForm(form);
          console.log("Employee updated successfully");
          this.update = false;
        });
      }
    
  }

  resetForm(form?:NgForm){
    if(form){
      form.reset();
    }
    this.es.selectedEmployee = new Employee();
     this.refreshEmployees(); 
  }

  refreshEmployees(){
    this.es.getAllEmployees().subscribe((res)=>{
      this.es.employees = res as Employee[];
    });
  }

  updateEmp(emp:Employee){
    this.update = true;
    this.es.selectedEmployee = emp;
  }

  deleteEmp(id:number | undefined)
  {
    if(id)
    {
      if(confirm('Are you sure you want to delete this employee?'))
      {
        this.es.deleteEmployee(id).subscribe((res)=>{
          console.log("Employee deleted successfully");
          this.refreshEmployees();
        });
      }
    }

  }

}
