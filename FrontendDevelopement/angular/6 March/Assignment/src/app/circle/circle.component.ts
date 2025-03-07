import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  imports: [FormsModule,CommonModule],
  selector: 'app-circle',
  templateUrl: './circle.component.html',
  styleUrls: ['./circle.component.css']
})
export class CircleComponent {
  radius: number = 0;
  diameter: number | null = null;
  area: number | null = null;
  perimeter: number | null = null;

  calculate(): void {
    if (this.radius > 0) {
      this.diameter = 2 * this.radius;
      this.area = Math.PI * this.radius * this.radius;
      this.perimeter = 2 * Math.PI * this.radius;
    } else {
      this.diameter = this.area = this.perimeter = null;
    }
  }
}
