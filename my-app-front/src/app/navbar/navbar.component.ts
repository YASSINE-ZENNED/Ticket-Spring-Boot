import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {SharedService} from "../shared.service";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})

export class NavbarComponent {
  loggedIn: boolean = false;
  user: string= "";
  IsAdmin: boolean = false;

  constructor(public sharedService: SharedService, private router: Router,private globalSrv: AuthService) {

    globalSrv.itemValue.subscribe((nextValue) => {

      this.checkLoginStatus();
    })

  }


  ngOnInit() {

    this.checkLoginStatus();

  }

  checkLoginStatus() {
    const token = localStorage.getItem('token');

    const user = localStorage.getItem('user');

    this.IsAdmin = localStorage.getItem('Role')== 'admin';

    console.log("IsAdmin :::::::-------------------:::::",this.IsAdmin);

    console.log("Token :::::::-------------------:::::",user);

    this.loggedIn = token !== null; // Set to true if token exists, false otherwise
    this.user = user?? "User"; // Set to true if token exists, false otherwise
  }
  logout() {

    console.log('Logging out...');
    localStorage.clear()
    this.sharedService.sharedVariable = null;
    location.reload();

  }
}
