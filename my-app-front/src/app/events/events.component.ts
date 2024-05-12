import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {SharedService} from "../shared.service";
interface Event {
  ownerID: string;
  id: number;
  eventName: string;
  date: string | null;
  location: string;
  numberOfSeats: number;
}
@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrl: './events.component.css'
})
export class EventsComponent {
  events: Event[] = [];

  userId: string = localStorage.getItem('userId')??'not set';

  constructor(private http: HttpClient, private router: Router,public sharedService: SharedService) {}

  errorMessage = '';
  openTicketPage(event: Event): void {
    console.log('Event:', event);

    this.router.navigate(['/addT', event.date, event.location, event.eventName, event.numberOfSeats, event.id]);

  }
  openEditPage(event: Event): void {
    console.log('Event:', event);

    this.router.navigate(['/editEvent', event.date, event.location, event.eventName, event.numberOfSeats, event.id, event.ownerID]);

  }

  ngOnInit() {

    let userId = localStorage.getItem('userId')??'0';


    console.log('User id is  !' + userId);

    console.log('Getting __________________________ successful!');

   // let   token = this.sharedService.Token ;
   //  const headers = new HttpHeaders({
   //    'Authorization': `Bearer ${token}`
   //  });

    this.http.get<any>( // Specify the expected response type
      'http://localhost:8080/AllEvents'
    ).subscribe(
      response => {
        this.events = response;

        console.log('Getting successful!', response);

        this.errorMessage = ''; // Clear any previous error message

      },
      error => {
        console.error('getting events  failed:', error);
        this.errorMessage = 'getting events. Please check your credentials.'; // Set an error message
      }
    );

  }

}
