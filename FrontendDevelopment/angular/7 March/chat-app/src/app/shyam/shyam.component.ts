import { Component } from '@angular/core';
import { ChatService } from '../services/chat.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-shyam',
  imports: [CommonModule],
  templateUrl: './shyam.component.html',
  styleUrl: './shyam.component.css'
})
export class ShyamComponent {

  name:string = "Ram";
  
    constructor(public cs:ChatService){
  
    }
  
    sendMessage(msg:string){
      this.cs.chatMessage(this.name + ": " + msg);
    }
}
