import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-upload-cloth1',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './upload-cloth1.component.html',
  styleUrls: ['./upload-cloth1.component.scss']
})
export class UploadCloth1Component {

  onPrestarClick(): void {
    // Lógica para 'Prestar'
    console.log('Prestar click');
  }

  onIntercambiarClick(): void {
    // Lógica para 'Intercambiar'
    console.log('Intercambiar click');
  }

}