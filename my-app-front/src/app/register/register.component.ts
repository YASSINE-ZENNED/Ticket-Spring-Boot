import { Component } from '@angular/core';
import {HttpClient,HttpErrorResponse } from "@angular/common/http";
import {Observable} from "rxjs";



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

  constructor(private http: HttpClient) {}


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
          this.errorMessage = '';
        },
        error => {
          console.error('register failed:', error);
          this.errorMessage = error.error ? error.error.message : 'Registration failed. Please try again.';
        }
      );



  }

}
