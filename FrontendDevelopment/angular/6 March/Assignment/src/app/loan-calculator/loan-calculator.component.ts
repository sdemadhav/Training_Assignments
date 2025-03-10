import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-loan-calculator',
  imports: [CommonModule,FormsModule],
  templateUrl: './loan-calculator.component.html',
  styleUrls: ['./loan-calculator.component.css']
})
export class LoanCalculatorComponent {
  applicantName: string = '';
  loanType: string = '';
  interestRate: number = 0;
  tenure: number | null = null;
  amount: number | null = null;
  emi: number | null = null;
  errorMessage: string = '';

  loanDetails: { [key: string]: { rate: number, maxTenure: number } } = {
    'Home Loan': { rate: 9, maxTenure: 30 },
    'Car Loan': { rate: 11, maxTenure: 7 },
    'Personal Loan': { rate: 15, maxTenure: 5 }
  };

  onLoanTypeChange() {
    if (this.loanType) {
      this.interestRate = this.loanDetails[this.loanType].rate;
      this.tenure = null;
      this.emi = null;
      this.errorMessage = '';
    }
  }

  onInputChange() {
    if (!this.loanType || !this.amount || !this.tenure) {
      this.emi = null;
      return;
    }

    const maxTenure = this.loanDetails[this.loanType].maxTenure;
    if (this.tenure > maxTenure) {
      this.errorMessage = `Maximum tenure for ${this.loanType} is ${maxTenure} years.`;
      this.emi = null;
      return;
    } else {
      this.errorMessage = '';
    }

    this.calculateEMI();
  }

  calculateEMI() {
    const r = this.interestRate / 12 / 100; 
    const n = this.tenure! * 12; 
    const P = this.amount!;

    this.emi = (P * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
  }

  getLoanTypes(): string[] {
    return Object.keys(this.loanDetails);
  }
  
}
