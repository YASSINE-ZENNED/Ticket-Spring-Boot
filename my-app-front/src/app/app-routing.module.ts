import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { NotFoundComponent } from './not-found/not-found.component';

const routes:Routes=[
  {path:'', component:HomeComponent},
  {path:'about', component:AboutComponent},
  {path:'register', component:RegisterComponent},
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
