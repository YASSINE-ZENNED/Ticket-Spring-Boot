import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {SharedService} from "../shared.service";

interface TicketRequest {
  ticketTime: string;
  userId: string,
  eventId: number;
  fullName: string;
  eventDate: string;
  numberOfSeats: number;
  location: string;
}


@Component({
  selector: 'app-add-ticket',
  templateUrl: './add-ticket.component.html',
  styleUrl: './add-ticket.component.css'
})
export class AddTicketComponent {

  event: any;
  date: string | null = null;
  location: string | null = null;
  numberOfSeats: number = 0;

  userId: string = localStorage.getItem('userId')??'not set';


  constructor(private http: HttpClient, private router: Router,public sharedService: SharedService,private route: ActivatedRoute) {


  }
  errorMessage = '';
  ticketRequest: TicketRequest = {
    ticketTime: '',
    userId: '',
    eventId: 0,
    fullName: '',
    eventDate: this.date ? this.date : '',
    numberOfSeats: 0,
    location: this.location ? this.location : '',
  };
  ngOnInit(): void {



    this.route.paramMap.subscribe(params => {
      this.date = params.get('date');
      this.location = params.get('location');
      this.numberOfSeats = Number(params.get('numberOfSeats'));
      this.event = params.get('eventName');
      this.ticketRequest.userId = params.get('ownerID')??'no id';

    });

      console.log('Getting __________________________ successful!');
    console.log(this.date);
      console.log(this.location);
    console.log('Getting __________________________ successful!');

    this.ticketRequest.eventDate = this.date? this.date : '';
    this.ticketRequest.location = this.location ? this.location : '';
    this.ticketRequest.eventId = Number(this.route.snapshot.paramMap.get('id'));
    this.ticketRequest.userId = this.userId;

    this.ticketRequest.ticketTime = this.date? this.date : '';

    console.log(  this.ticketRequest.eventId);


    // this.ticketRequest.numberOfSeats = this.numberOfSeats ? this.numberOfSeats : 0;
  }





  onSubmit() {
    console.log(this.ticketRequest);
    console.log('Getting __________________________ successful!');

    let   token = this.sharedService.Token ;

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    this.http.post<any>( // Specify the expected response type
      'http://localhost:8083/ticket',
      this.ticketRequest,
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
