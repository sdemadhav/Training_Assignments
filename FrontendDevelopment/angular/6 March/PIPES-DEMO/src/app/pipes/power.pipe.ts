import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'power'
})
export class PowerPipe implements PipeTransform {

  transform(value: number, ...args: number[]): number {
    let count = args[0]
    let result = value;
    for(let i=0 ; i<count; i++){
      result = result*value;
    }
    return result;
  }

}
