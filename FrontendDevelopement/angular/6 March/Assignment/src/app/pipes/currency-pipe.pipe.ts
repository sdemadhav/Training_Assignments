import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'currencyPipe'
})
export class CurrencyPipePipe implements PipeTransform {

  transform(value: number, ...args: String[]): number {
    
    switch (args[0]) {
      case 'USD':
        return value * 0.012;
      case 'JPY':
        return value * 1.75;
      case 'SAR':
        return value * 0.045;
      case 'EUR':
        return value * 0.011;
    }
    return value
  }

}
