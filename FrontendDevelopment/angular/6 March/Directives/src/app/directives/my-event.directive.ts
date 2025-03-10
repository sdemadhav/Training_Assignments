import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[MyEvent]'
})
export class MyEventDirective {

  constructor() { }

  

  @HostListener('click')
    abc(){
    console.log("You have clicked on this tag content")
  }

  @HostListener('mouseenter')
    xyz(){
    console.log("You have hovered on this tag content")
  }

}
