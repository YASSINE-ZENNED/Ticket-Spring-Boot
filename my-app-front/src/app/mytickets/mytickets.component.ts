import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {SharedService} from "../shared.service";
interface Ticket {
  id: number;
  eventId: number;
  userId: string;
  ticketTime: string;
  numberOfSeats: number;
  location: string;
  ticketId: string;
}
@Component({
  selector: 'app-mytickets',
  templateUrl: './mytickets.component.html',
  styleUrl: './mytickets.component.css'
})
export class MyticketsComponent {

  constructor(private http: HttpClient, private router: Router,public sharedService: SharedService) {}


  tickets: Ticket[] = [

  ];
  canCancel(ticketTime: string): boolean {
    const now = new Date();
    const eventDate = new Date(ticketTime);
    const diff = eventDate.getTime() - now.getTime();
    return diff > 24 * 60 * 60 * 1000; // Difference greater than 24 hours in milliseconds
  }

  deleteTicket(  Tid : number){

    this.http.delete<any>( // Specify the expected response type

      `http://localhost:8083/ticket/${Tid}`
    ).subscribe(
      response => {
        this.tickets = response;

        console.log('delete successful!', response);

        location.reload();

      },
      error => {
        console.error('delete events  failed:', error);
      }
    );

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

      `http://localhost:8083/ticket/${userId}`
    ).subscribe(
      response => {
        this.tickets = response;

        console.log('Getting successful!', response);


      },
      error => {
        console.error('getting events  failed:', error);
      }
    );

  }

}
