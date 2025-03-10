import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RoleService } from '../services/role.service';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  msg:string = "";

  constructor(private router: Router, private roleService:RoleService ) { }

  abc(event:any){
    event.preventDefault();
    let uname = event.target.elements[0].value;
    let pwd = event.target.elements[1].value;
    let role = event.target.elements[2].value;
    this.roleService.role = role;

    console.log(role);
    console.log("username"+uname+", password: "+pwd);
    if(uname==pwd){
      this.msg = "You are not authorized to access this page, its for managers only";
      this.router.navigate(['welcome']);
    }
    else{
      this.msg = "Invalid Credentials, please try again";
      this.router.navigate(['netbanking']);
    }
  }

}
