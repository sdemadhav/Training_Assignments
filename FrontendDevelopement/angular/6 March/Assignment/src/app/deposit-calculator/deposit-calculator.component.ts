import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-deposit-calculator',
  imports: [CommonModule,FormsModule],
  templateUrl: './deposit-calculator.component.html',
  styleUrl: './deposit-calculator.component.css'
})
export class DepositCalculatorComponent {

  applicantName: string = '';
  principal_amount: number = 0;
  tenure: number = 0;
  interestRate: number = 7;
  maturityAmount: number | null = null;
  errorMessage: string = '';

  calculateMaturityAmount() {
    if (this.applicantName && this.principal_amount > 0 && this.tenure > 0 && this.interestRate > 0) {
      this.maturityAmount = this.principal_amount * Math.pow(1 + this.interestRate / 100, this.tenure);
    } else {
      this.maturityAmount = null;
      this.errorMessage = 'Please enter valid values for Principal Amount, Tenure, and Interest Rate.';
    }
  }

  onInputChange() {
    this.calculateMaturityAmount();
  }
}
