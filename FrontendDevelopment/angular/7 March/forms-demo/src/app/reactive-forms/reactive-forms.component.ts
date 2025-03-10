import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-reactive-forms',
  imports: [FormsModule,ReactiveFormsModule],
  templateUrl: './reactive-forms.component.html',
  styleUrl: './reactive-forms.component.css'
})
export class ReactiveFormsComponent {

  employeeForm:any
  
  ngOnInit(){
    this.employeeForm = new FormGroup({
      uname: new FormControl('',Validators.required),
      age: new FormControl('',
                          Validators.compose(
                            [Validators.required,
                              Validators.min(18),
                              Validators.max(60)
                            ])),
      salary: new FormControl(21000),
      designation: new FormControl('',this.desigValidator)
    })
  }

  desigValidator(control:any): any{
    if(control.value.length<5){
      return {uname:true}
    }
  }
  abc(obj: any) {
    console.log(obj);

  }
}
