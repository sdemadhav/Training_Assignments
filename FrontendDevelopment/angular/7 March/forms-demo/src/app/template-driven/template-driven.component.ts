import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-template-driven',
  imports: [FormsModule],
  templateUrl: './template-driven.component.html',
  styleUrl: './template-driven.component.css'
})
export class TemplateDrivenComponent {

  name: string = '';
  age: number = 0;
  salary: number = 0;
  designation: string = '';
  abc(obj: any) {
    console.log(obj);
  }
}
