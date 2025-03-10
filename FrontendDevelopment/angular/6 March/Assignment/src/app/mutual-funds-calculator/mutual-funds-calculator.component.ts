import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-mutual-funds-calculator',
  imports: [CommonModule,FormsModule],
  templateUrl: './mutual-funds-calculator.component.html',
  styleUrl: './mutual-funds-calculator.component.css'
})
export class MutualFundsCalculatorComponent {

  applicantName: string = '';
  monthly_sip: number = 0;
  tenure: number = 0;
  interestRate: number = 0;
  totalInvestment: number | null = null;
  interestEarned: number | null = null;
  totalValue: number | null = null;
  errorMessage: string = '';

  calculateTotalValue() {
    if (this.applicantName && this.monthly_sip > 0 && this.tenure > 0 && this.interestRate > 0) {
      this.totalInvestment = this.monthly_sip * this.tenure * 12;
      this.interestEarned = this.totalInvestment * (this.interestRate / 100);
      this.totalValue = this.totalInvestment + this.interestEarned;
    } else {
      this.totalInvestment = null;
      this.interestEarned = null;
      this.totalValue = null;
      this.errorMessage = 'Please enter valid values for Monthly SIP, Tenure, and Interest Rate.';
  }

  }

  onInputChange():void{
    this.calculateTotalValue();
  }
}
