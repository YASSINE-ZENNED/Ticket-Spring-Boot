import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {SharedService} from "../shared.service";

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrl: './events.component.css'
})
export class EventsComponent {
  events: any[] = [];

  constructor(private http: HttpClient, private router: Router,public sharedService: SharedService) {}

  errorMessage = '';


  ngOnInit() {
    console.log('Getting __________________________ successful!');

   // let   token = this.sharedService.Token ;
   //
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
