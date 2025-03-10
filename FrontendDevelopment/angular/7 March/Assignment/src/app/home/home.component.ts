import { Component } from '@angular/core';
import { HandleCustomerService } from '../services/handle-customer.service';

@Component({
  selector: 'app-home',
  imports: [HandleCustomerService],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

 
  constructor(private handleCustomer: HandleCustomerService) {
  
  }


}


