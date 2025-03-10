import { Injectable } from '@angular/core';
import { LogService } from './log.service';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  public messages: string[] = [];

  constructor(private logService: LogService) {
    console.log("chat service Object created");
   }

   chatMessage(msg:string){
    this.messages.push(msg);
    this.logService.logMessage(msg);
   }
}
