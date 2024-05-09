import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {SharedService} from "../shared.service";
 interface Event {
  eventName: string;
  date: string | null;
  location: string;
  numberOfSeats: number;
}

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrl: './create-event.component.css'
})
export class CreateEventComponent {
  constructor(private http: HttpClient, private router: Router,public sharedService: SharedService) {}
  errorMessage = '';

  event: Event = {
    eventName: "Event3",
    date: new Date().toISOString().split('T')[0],
    location: "tekup",
    numberOfSeats: 90
  };
  onSubmit() {
    console.log('Getting __________________________ successful!');

    let   token = this.sharedService.Token ;

     const headers = new HttpHeaders({
       'Authorization': `Bearer ${token}`
     });

    this.http.post<any>( // Specify the expected response type
      'http://localhost:8082/events/CreateEvent',
      this.event,
      { headers }
          ).subscribe(
      response => {

        console.log('adding successful!', response);

        this.errorMessage = ''; // Clear any previous error message

      },
      error => {
        console.error('adding events  failed:', error);
        this.errorMessage = 'adding events. Please check your credentials.'; // Set an error message
      }
    );

  }


}
