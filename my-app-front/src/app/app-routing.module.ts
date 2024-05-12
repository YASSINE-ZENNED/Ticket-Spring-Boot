import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import {EventsComponent} from './events/events.component';
import { CreateEventComponent } from './create-event/create-event.component';
import { AddTicketComponent } from './add-ticket/add-ticket.component';
import { NotFoundComponent } from './not-found/not-found.component';
import {EditEventComponent} from "./edit-event/edit-event.component";
import {MyticketsComponent} from "./mytickets/mytickets.component";

const routes:Routes=[
  {path:'', component:HomeComponent},
  {
    path: 'addT/:date/:location/:eventName/:numberOfSeats/:id', // Dynamic segments in the path
    component: AddTicketComponent
  },  {
    path: 'editEvent/:date/:location/:eventName/:numberOfSeats/:id/:ownerID', // Dynamic segments in the path
    component: EditEventComponent
  },
  {path:'about', component:AboutComponent},
  {path:'register', component:RegisterComponent},
  {path:'login', component:LoginComponent},
  {path:'events', component:EventsComponent},
  {path:'editEvent', component:EditEventComponent},
  {path:'mytickets', component:MyticketsComponent},

  {path:'createEvent',component: CreateEventComponent},
  {path:'addTicket',component: AddTicketComponent},
  {path:'**', component:NotFoundComponent}


];

@NgModule({
  declarations: [
    NotFoundComponent
  ],
  imports: [RouterModule.forRoot(routes),
    CommonModule
  ],
  exports:[RouterModule]
})
export class AppRoutingModule { }
