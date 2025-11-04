import { Component } from '@angular/core';
import { IonicModule } from '@ionic/angular';
import { LogoConFondoComponent } from "../logo-con-fondo/logo-con-fondo.component";
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss'],
  standalone: true,
  imports: [
    IonicModule,
    FormsModule,
    LogoConFondoComponent
  ]
})
export class SignUpComponent {

  constructor(private router: Router) { }

  email: string = '';
  password: string = '';
  confirmPassword: string = '';

  get passwordsMatch(): boolean {
    return this.password.length > 0 && this.password === this.confirmPassword;
  }

  get hasMinLength(): boolean {
    return this.password.length >= 8;
  }

  get isValidEmail(): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(this.email);
  }

  get isFormValid(): boolean {
    return this.passwordsMatch && this.hasMinLength && this.isValidEmail;
  }

  onSubmit() {
    if (this.isFormValid) {
      this.router.navigate(['/login'])
      console.log('Formulario válido', {
        email: this.email,
        password: this.password
      });
    }
  }
}
