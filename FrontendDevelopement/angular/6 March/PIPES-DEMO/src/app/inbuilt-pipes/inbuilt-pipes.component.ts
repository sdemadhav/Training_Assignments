import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-inbuilt-pipes',
  imports: [CommonModule],
  templateUrl: './inbuilt-pipes.component.html',
  styleUrl: './inbuilt-pipes.component.css'
})
export class InbuiltPipesComponent {
 str: string = 'Hello Everybody';
 amt : number = 234567;
 dt : Date = new Date();
}
