import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { NavegationBarComponent } from './navegation-bar/navegation-bar.component';
import { SplashComponent } from './splash/splash.component';
import { HomeComponent } from './home/home.component';
import { CarrouselHorizontalComponent } from './carrousel-horizontal/carrousel-horizontal.component';

export const routes: Routes = [

  {path: '', redirectTo: '/splash', pathMatch: 'full'},
  { path: 'login', component: LoginComponent},
  { path: 'sign-up', component: SignUpComponent },
  { path: 'navegation-bar', component: NavegationBarComponent },
  { path: 'splash', component: SplashComponent },
  { path: 'home', component: HomeComponent },
  { path: 'home', component: CarrouselHorizontalComponent },
];
