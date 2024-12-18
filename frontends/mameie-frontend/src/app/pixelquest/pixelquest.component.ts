import { Component } from '@angular/core';
import { PixelContentComponent } from "./pixel-content/pixel-content.component";

@Component({
  selector: 'app-pixelquest',
  standalone: true,
  imports: [PixelContentComponent],
  templateUrl: './pixelquest.component.html',
  styleUrl: './pixelquest.component.scss'
})
export class PixelquestComponent {

}
