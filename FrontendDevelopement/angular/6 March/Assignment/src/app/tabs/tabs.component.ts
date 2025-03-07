import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CircleComponent } from '../circle/circle.component';
import { CurrencyConverterComponent } from '../currency-converter/currency-converter.component';
import { LoanCalculatorComponent } from '../loan-calculator/loan-calculator.component';
import { DepositCalculatorComponent } from '../deposit-calculator/deposit-calculator.component';
import { MutualFundsCalculatorComponent } from '../mutual-funds-calculator/mutual-funds-calculator.component';

@Component({
  selector: 'app-tabs',
  imports: [CommonModule,CircleComponent,CurrencyConverterComponent,LoanCalculatorComponent,DepositCalculatorComponent,MutualFundsCalculatorComponent],
  templateUrl: './tabs.component.html',
  styleUrls: ['./tabs.component.css']
})
export class TabsComponent {
  selectedTab: string = 'CircleCalc';
  selectedSidebarOption: string = 'Loan';

  selectTab(tab: string) {
    this.selectedTab = tab;
    if (tab !== 'LoanDepositMutualFund') {
      this.selectedSidebarOption = ''; 
    }
  }

  selectSidebarOption(option: string) {
    this.selectedSidebarOption = option;
  }
}
