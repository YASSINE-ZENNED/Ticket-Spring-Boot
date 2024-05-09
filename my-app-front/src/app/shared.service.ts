import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root' // This makes the service a singleton
})
export class SharedService {
  public sharedVariable: any;
  public Token: any;
  // Replace 'any' with your data type for better type checking

  constructor() {
  }
}
