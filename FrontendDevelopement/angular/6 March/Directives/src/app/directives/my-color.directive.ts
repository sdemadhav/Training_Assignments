import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[MyColor]'
})
export class MyColorDirective {

  constructor(private element: ElementRef) { }


  ngOnInit() {
    this.element.nativeElement.style.color = 'red';
  }
}
