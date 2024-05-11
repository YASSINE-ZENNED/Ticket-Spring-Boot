import { Component,ChangeDetectorRef } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import { SharedService } from '../shared.service';

import { jwtDecode } from "jwt-decode";
import {AuthService} from "../auth.service";

interface LoginCredentials {
  username: string;
  password: string;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  userRoles: string[] | undefined;

  email = '';
  password = '';
  errorMessage = '';

  constructor(private http: HttpClient, private router: Router,public sharedService: SharedService,private globalSrv: AuthService) {}




  onSubmit() {


    console.log('Email:', this.email);
    console.log('Password:', this.password);

    const credentials = {
      username: this.email,
      password: this.password
    };

    console.log("Credentials", credentials);
    this.http.post<any>( // Specify the expected response type
      'http://localhost:8080/Login',
      credentials
    ).subscribe(
      response => {
        console.log('Login successful!', response);
        const token = response.token; // Access data from the response object

        this.sharedService.sharedVariable = this.email;

        this.sharedService.Token = token;

        localStorage.setItem('token', this.sharedService.Token)
        localStorage.setItem('user', this.sharedService.sharedVariable)
        this.globalSrv.theItem = this.sharedService.Token; // this change will broadcast to every subscriber like below component
        console.log("Token :::::::-------------------:::::",localStorage.getItem('token')
        );

      try {
        const decoded : any = jwtDecode(this.sharedService.Token);

        this.sharedService.Role = decoded?.resource_access['Ticket-App']?.roles[0]?? null;

         console.log("Role ::::::::::::",this.sharedService.Role);

      }
      catch(Error){
        console.log("Error ::::::::::::",Error);
      }


        this.router.navigate(['']);

        this.errorMessage = ''; // Clear any previous error message
      },
      error => {
        console.error('Login failed:', error);
        this.errorMessage = 'Login failed. Please check your credentials.'; // Set an error message
      }
    );
  }


}
