import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { HandleCustomerService } from '../services/handle-customer.service';

@Component({
  selector: 'app-register',
  imports: [CommonModule,ReactiveFormsModule,FormsModule,HandleCustomerService],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm: FormGroup<{
    customerId: FormControl<string>;
    username: FormControl<string>;
    password: FormControl<string>;
    confirmPassword: FormControl<string>;
    accountNo: FormControl<string>;
  }>;

  submitted = false;

  constructor(private fb: FormBuilder, private handleCustomer: HandleCustomerService) {
    this.registerForm = this.fb.nonNullable.group({
      customerId: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.pattern(/^\d{7}$/)] }),
      username: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.minLength(5), Validators.pattern(/^[a-zA-Z]+$/)] }),
      password: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.minLength(8), Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\W).*$/)] }),
      confirmPassword: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
      accountNo: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.minLength(6), Validators.pattern(/^\d+$/)] }),
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(form: AbstractControl) {
    return form.get('password')?.value === form.get('confirmPassword')?.value ? null : { mismatch: true };
  }

  onSubmit() {
    this.submitted = true;
    if (this.registerForm.valid) {
      const customerData = this.registerForm.value;
      this.handleCustomer.addCustomer(customerData); 
      this.handleCustomer.setRegistered(true);
      console.log('All customers:', this.handleCustomer.getCustomers());
    }
    else{
      console.log('Invalid form');
    }
  }

  get f() { return this.registerForm.controls; }
}