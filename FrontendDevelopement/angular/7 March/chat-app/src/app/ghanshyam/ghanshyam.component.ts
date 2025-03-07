import { Component } from '@angular/core';
import { ChatService } from '../services/chat.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ghanshyam',
  imports: [CommonModule],
  templateUrl: './ghanshyam.component.html',
  styleUrl: './ghanshyam.component.css'
})
export class GhanshyamComponent {
  name:string = "Shyam";
  
    constructor(public cs:ChatService){
  
    }
  
    sendMessage(msg:string){
      this.cs.chatMessage(this.name + ": " + msg);
    }
}
