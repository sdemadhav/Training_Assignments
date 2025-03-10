import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CurrencyPipePipe } from '../pipes/currency-pipe.pipe';
import { CommonModule } from '@angular/common';
import { CurrencyPipe } from '@angular/common';

@Component({
  imports: [FormsModule,CurrencyPipePipe,CommonModule,CurrencyPipe],
  selector: 'app-currency-converter',
  templateUrl: './currency-converter.component.html',
  styleUrls: ['./currency-converter.component.css']
})
export class CurrencyConverterComponent {
  amountInINR: number = 0;
  convertedAmount: number | null = null;
  selectedCurrency: string = '';

  exchangeRates: { [key: string]: number } = {
    USD: 0.012, 
    JPY: 1.75,   
    SAR: 0.045, 
    EUR: 0.011  
  };

  convertCurrency(currencyType: string) {
    this.selectedCurrency = currencyType;
    this.convertedAmount = this.amountInINR * this.exchangeRates[currencyType];
  }
}
