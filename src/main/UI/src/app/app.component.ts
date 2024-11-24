import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {HttpClient, HttpResponse,HttpHeaders} from "@angular/common/http";
import { Observable } from 'rxjs';
import {map} from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(private httpClient:HttpClient){}

  private baseURL:string='http://localhost:8080';

  private getUrl:string = this.baseURL + '/room/reservation/v1/';
  private postUrl:string = this.baseURL + '/room/reservation/v1';
  public submitted!:boolean;
  welcomeMessage: string = '';
  meetingInvitationMessage: string = '';
  roomsearch! : FormGroup;
  rooms! : Room[];
  request!:ReserveRoomRequest;
  currentCheckInVal!:string;
  currentCheckOutVal!:string;

    ngOnInit(){
      this.roomsearch= new FormGroup({
        checkin: new FormControl(' '),
        checkout: new FormControl(' ')
      });

    const roomsearchValueChanges$ = this.roomsearch.valueChanges;

    // subscribe to the stream
    roomsearchValueChanges$.subscribe(x => {
      this.currentCheckInVal = x.checkin;
      this.currentCheckOutVal = x.checkout;
    });

      // Fetch welcome message on component initialization
      this.getWelcomeMessage().subscribe(
        messages => this.welcomeMessage = messages.join(' '),
        error => console.error('Error fetching welcome message', error)
      );

      this.getMeetingInvitationMessage().subscribe(
        messages => this.meetingInvitationMessage = messages.join(' '),
        error => console.error('Error fetching invitation message', error)
      );

    }

    onSubmit({value,valid}:{value:Roomsearch,valid:boolean}){
      this.getAll().subscribe(

        rooms => {console.log(Object.values(rooms)[0]);this.rooms=<Room[]>Object.values(rooms)[0]; }


      );
    }
    reserveRoom(value:string){
      this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal);

      this.createReservation(this.request);
    }
    createReservation(body:ReserveRoomRequest) {
      let bodyString = JSON.stringify(body); // Stringify payload
      let headers = new Headers({'Content-Type': 'application/json'}); // ... Set content type to JSON

     const options = {
      headers: new HttpHeaders().append('key', 'value'),

    }

      this.httpClient.post(this.postUrl, body, options)
        .subscribe(res => console.log(res));
    }

    getAll(): Observable<any> {


       return this.httpClient.get(this.baseURL + '/room/reservation/v1?checkin='+ this.currentCheckInVal + '&checkout='+this.currentCheckOutVal, {responseType: 'json'});
    }

  // Method to fetch the welcome message from the backend
  // Makes an HTTP GET request to the '/api/welcome/messages' endpoint
  getWelcomeMessage(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.baseURL + '/api/welcome/messages');
  }

  // Method to fetch the meeting invitation message from the backend
  // Makes an HTTP GET request to the '/api/datetime/results' endpoint
  getMeetingInvitationMessage(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.baseURL + '/api/datetime/results');
  }

}

export interface Roomsearch{
    checkin:string;
    checkout:string;
  }

export interface Room{
  id:string;
  roomNumber:string;
  price:string;
  links:string;

}
export class ReserveRoomRequest {
  roomId:string;
  checkin:string;
  checkout:string;

  constructor(roomId:string,
              checkin:string,
              checkout:string) {

    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }
}
