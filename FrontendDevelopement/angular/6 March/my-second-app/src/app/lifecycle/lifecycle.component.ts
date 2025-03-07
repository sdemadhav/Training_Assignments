import { Component, DoCheck, OnChanges, OnDestroy, OnInit } from "@angular/core";

@Component({
  selector: 'app-lifecycle',
  templateUrl: './lifecycle.component.html',
  styleUrls: ['./lifecycle.component.css']
})
export class LifecycleComponent implements OnInit,OnChanges,DoCheck,OnDestroy {}

constructor() {
  console.log('constructor');
}

ngonchanges() {
  console.log('ngOnChanges called...');
}

ngoninit() {
  console.log('ngOnInit called...');
}

ngdocheck() {
  console.log('ngDoCheck called...');
}

ngondestroy() {
  console.log('ngOnDestroy called...');
}