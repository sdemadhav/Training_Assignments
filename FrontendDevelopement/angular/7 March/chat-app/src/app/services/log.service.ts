import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  constructor() {
    console.log("log service Object created");
   }

   logMessage(msg:String){
    console.log(msg);
   }
}
