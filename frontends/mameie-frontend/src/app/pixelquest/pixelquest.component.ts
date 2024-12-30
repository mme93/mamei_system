import { Component } from '@angular/core';
import { PixelContentComponent } from "./pixel-content/pixel-content.component";
import { PixelLoginComponent } from './pixel-login/pixel-login.component';

@Component({
  selector: 'app-pixelquest',
  standalone: true,
  imports: [PixelContentComponent,PixelLoginComponent],
  templateUrl: './pixelquest.component.html',
  styleUrl: './pixelquest.component.scss'
})
export class PixelquestComponent {

}
