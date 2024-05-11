import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  itemValue = new BehaviorSubject(this.theItem)?? '';

  set theItem(value) {

    this.itemValue.next(value); // this will make sure to tell every subscriber about the change.
    localStorage.setItem('LT', value?? '');

  }

  get theItem() {
    return localStorage.getItem('theItem');
  }

  constructor() { }
}
