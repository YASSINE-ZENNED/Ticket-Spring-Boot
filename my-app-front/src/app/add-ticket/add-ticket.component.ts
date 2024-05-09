import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {SharedService} from "../shared.service";

interface Ticket {
  eventId: number
  numberOfSeats: number;
}

@Component({
  selector: 'app-add-ticket',
  templateUrl: './add-ticket.component.html',
  styleUrl: './add-ticket.component.css'
})
export class AddTicketComponent {

  constructor(private http: HttpClient, private router: Router,public sharedService: SharedService) {}
  errorMessage = '';

  ticket: Ticket = {
    eventId: 1,
    numberOfSeats: 90
  };

  onSubmit() {
    console.log('Getting __________________________ successful!');

    let   token = this.sharedService.Token ;

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    this.http.post<any>( // Specify the expected response type
      'http://localhost:8083/ticket',
      this.ticket,
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
