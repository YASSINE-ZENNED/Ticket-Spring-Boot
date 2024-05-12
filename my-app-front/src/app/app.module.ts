import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';

import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { AppRoutingModule } from './app-routing.module';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { EventsComponent } from './events/events.component';
import {FormsModule} from "@angular/forms";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { CreateEventComponent } from './create-event/create-event.component';
import { AddTicketComponent } from './add-ticket/add-ticket.component';
import { EditEventComponent } from './edit-event/edit-event.component';
import { MyticketsComponent } from './mytickets/mytickets.component';
import { QRCodeModule } from 'angularx-qrcode';

@NgModule({
  declarations: [
    AppComponent,

    NavbarComponent,
    FooterComponent,
    RegisterComponent,
    LoginComponent,
    EventsComponent,
    CreateEventComponent,
    AddTicketComponent,
    EditEventComponent,
    MyticketsComponent,


  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
      HttpClientModule,
      QRCodeModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
