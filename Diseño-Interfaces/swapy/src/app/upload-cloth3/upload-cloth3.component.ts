import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavegationBarComponent } from "../navegation-bar/navegation-bar.component";
import { SloganLogoComponent } from "../slogan-logo/slogan-logo.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-upload-cloth1',
  standalone: true,
  imports: [CommonModule, NavegationBarComponent, SloganLogoComponent],
  templateUrl: './upload-cloth3.component.html',
  styleUrls: ['./upload-cloth3.component.scss']
})
export class UploadCloth3Component {

  constructor(private router: Router) {}

  onPrestarClick(): void {
    this.router.navigate(['/brandSelector']);
  }

}