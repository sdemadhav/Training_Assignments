import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

interface Customer {
  customerId: number;
  username: string;
  password: string;
}

export class HandleCustomerService {

  private customers: any[] = []; 
  private  registered: boolean = false;
  private currentCustomerID = 0;

  constructor() {}

  addCustomer(customer: any) {
    this.customers.push(customer);
    console.log('Customer added successfully:', customer);
  }

  getCustomer(customerID: number): Customer | undefined {
    return this.customers.find(customer => customer.customerId === customerID);
  }

  setRegistered(value: boolean) {
    this.registered = value;
  }

  isRegistered() {
    return this.registered;
  }

  verifyUser(username: string, password: string) {
    for (const customer of this.customers) {
      if (customer.username === username && customer.password === password) {
        this.currentCustomerID = customer.customerId;
        return true;
      }
    }
    return false;  
  }
}

