import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'circleCalc'
})
export class CircleCalcPipe implements PipeTransform {

  transform(radius: number, type: string): number | string {
    if (!radius || radius <= 0) {
      return 'Invalid radius';
    }

    switch (type) {
      case 'diameter':
        return 2 * radius;
      case 'area':
        return Math.PI * radius * radius;
      case 'perimeter':
        return 2 * Math.PI * radius;
      default:
        return 'Invalid type';
    }
  }
}
