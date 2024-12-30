import { Component } from '@angular/core';
import { CanvasContentComponent } from "./canvas-content/canvas-content.component";

@Component({
  selector: 'app-canvas',
  standalone: true,
  imports: [CanvasContentComponent],
  templateUrl: './canvas.component.html',
  styleUrl: './canvas.component.scss'
})
export class CanvasComponent {

}
