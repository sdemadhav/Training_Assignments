import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-structural-directive',
  imports: [CommonModule],
  templateUrl: './structural-directive.component.html',
  styleUrl: './structural-directive.component.css'
})
export class StructuralDirectiveComponent {
  flag:boolean = true;
  count:number = 0;
  fruits:String[] = ['Apple','Mango','Banana','Grapes']

  toggle():void {
    this.flag = !this.flag
  }

  addFruit(f : String):void {
    this.fruits.push(f);
  }

  incrCount():void{
    this.count = this.count+1;
  }


}
