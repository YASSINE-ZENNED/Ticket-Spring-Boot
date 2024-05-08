import { Component } from '@angular/core';
import {HttpClient,HttpErrorResponse } from "@angular/common/http";
import {Observable} from "rxjs";
import { Router } from '@angular/router';
import {SharedService} from "../shared.service"; // Import Router



@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  username = '';
  email = '';
  password = '';
  errorMessage = '';

  constructor(private http: HttpClient, private router: Router,public sharedService: SharedService) {}


  onSubmit() {

    console.log('Username:', this.username);
    console.log('Email:', this.email);
    console.log('Password:', this.password);

    const credentials = {

      username: this.username,
      email: this.email,
      password: this.password
    };

    console.log("Credentials", credentials);
    this.http.post<any>('http://localhost:8080/SignUp', credentials, { responseType: 'text' as 'json' })
      .subscribe(
        response => {


          console.log('user created successfully!', response);
          this.http.post<any>( // Specify the expected response type
            'http://localhost:8080/Login',
            credentials
          ).subscribe(
            response => {
              console.log('Login successful!', response);
              const token = response.token; // Access data from the response object
              this.router.navigate(['']); // Navigate to the home page

              this.errorMessage = ''; // Clear any previous error message
            },
            error => {
              console.error('Login failed:', error);
              this.errorMessage = 'Login failed. Please check your credentials.'; // Set an error message
            }
          );


          this.errorMessage = '';
        },
        error => {
          console.error('register failed:', error);
          this.errorMessage = error.error ? error.error.message : 'Registration failed. Please try again.';
        }
      );



  }

}
