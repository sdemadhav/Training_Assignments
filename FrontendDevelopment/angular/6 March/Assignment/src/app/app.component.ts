import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CircleComponent } from './circle/circle.component';
import { TabsComponent } from './tabs/tabs.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [TabsComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Assignment';
}
