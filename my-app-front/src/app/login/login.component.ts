import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

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


  email = '';
  password = '';
  errorMessage = '';

  constructor(private http: HttpClient) {}



  onSubmit() {


    console.log('Email:', this.email);
    console.log('Password:', this.password);

    const credentials = {
      username: this.email,
      password: this.password
    };
    console.log("Credentials", credentials);
    this.http.post<any>( // Specify the expected response type
      'http://localhost:8081/auth/login',
      credentials
    ).subscribe(
      response => {
        console.log('Login successful!', response);
        const token = response.token; // Access data from the response object
        this.errorMessage = ''; // Clear any previous error message
      },
      error => {
        console.error('Login failed:', error);
        this.errorMessage = 'Login failed. Please check your credentials.'; // Set an error message
      }
    );










    // this.login(credentials).subscribe(
    //   response => {
    //     console.log('Login successful!', response);
    //     // Handle successful login (e.g., navigate to another page, show success message)
    //     this.errorMessage = ''; // Clear any previous error message
    //   },
    //   error => {
    //     console.error('Login failed:', error);
    //     this.errorMessage = 'Login failed. Please check your credentials.'; // Set an error message
    //   }
    // );
  }


}
