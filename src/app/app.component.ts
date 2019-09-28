import { Component, OnInit } from '@angular/core';
 import {FormControl, FormGroup} from '@angular/forms'

@Component({
     selector: 'app-root',
     templateUrl: './app.component.html',
     styleUrls: ['./app.component.css'] 
})
export class AppComponent implements OnInit  {
   
  public submitted : boolean;
    roomsearch : FormGroup;
    rooms: Room[];
  
    ngOnInit() { 
            this.roomsearch = new FormGroup({
    checkin: new FormControl(''),
    checkout: new FormControl('')
});
        this.rooms=ROOMS;
            }
        
onSubmit({ value, valid }:{ value: Roomsearch, valid: boolean }){
    console.log(value);
}

reserveRoom(value:string){
    console.log("Reserve Room :"+value);
}
      }
  export interface Roomsearch {
    checkin: string;
    checkout: string;
}

export interface Room {
    id: string;
    roomNumber: string;
    price: string;
    links: string;
}

var ROOMS:Room[]=[
{
        "id":"4234234",
        "roomNumber":"409",
        "price":"65",
        "links":""
        
        },
    {
        "id":"2323",
        "roomNumber":"419",
        "price":"615",
        "links":""
        
        },
    {
        "id":"65655",
        "roomNumber":"509",
        "price":"45",
        "links":""
        
        }
    ];