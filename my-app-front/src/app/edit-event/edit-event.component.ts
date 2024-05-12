import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {SharedService} from "../shared.service";
interface Event {
  eventID: number;
  ownerID: string;
  eventName: string;
  date: string | null;
  location: string;
  numberOfSeats: number;
}
@Component({
  selector: 'app-edit-event',
  templateUrl: './edit-event.component.html',
  styleUrl: './edit-event.component.css'
})
export class EditEventComponent { constructor(private http: HttpClient, private router: Router,public sharedService: SharedService,private route: ActivatedRoute) {}
  errorMessage = '';

  event: Event = {
    eventID: 0,
    ownerID:'0',
    eventName: "",
    date: null,
    location: "",
    numberOfSeats: 0,
  };
  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {
      this.event.date = params.get('date');
      this.event.location = params.get('location')??'';
      this.event.numberOfSeats = Number(params.get('numberOfSeats'));
      this.event.eventName = params.get('eventName')??'';
      this.event.ownerID = params.get('ownerID')??'0';
      this.event.eventID = Number(params.get('id'));


    });
  }

  deleteEvent(): void {
    if (window.confirm("Are you sure you want to delete this event?")) {


      this.http.delete<any>( // Specify the expected response type
        `http://localhost:8082/events/DeleteEvent/${this.event.eventID}`
        ,

      ).subscribe(
        response => {

          console.log('delete successful!', response);

          this.errorMessage = ''; // Clear any previous error message

        },
        error => {
          console.error('delete events  failed:', error);
          this.errorMessage = 'delete events. Please check your credentials.'; // Set an error message
        }
      );
      // Perform the deletion logic here
      console.log('Event Deleted:');
      // You might need to call a service to delete the event from the backend.
    }
  }
  onSubmit() {


    this.event.ownerID =localStorage.getItem('userId')??'0';


      console.log('updating event:', this.event);



    let   token = this.sharedService.Token ;

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    this.http.put<any>( // Specify the expected response type
      `http://localhost:8082/events/UpdateEvent/${this.event.eventID}`
      ,
      this.event,
      { headers }
    ).subscribe(
      response => {

        console.log('updating successful!', response);

        this.errorMessage = ''; // Clear any previous error message

      },
      error => {
        console.error('updating events  failed:', error);
        this.errorMessage = 'updating events. Please check your credentials.'; // Set an error message
      }
    );

  }


}
