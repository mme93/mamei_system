import { Component } from '@angular/core';
import { CanvasContentComponent } from "./canvas-content/canvas-content.component";
import { CanvasDeviceComponent } from "./canvas-device/canvas-device.component";

@Component({
  selector: 'app-canvas',
  standalone: true,
  imports: [CanvasContentComponent, CanvasDeviceComponent],
  templateUrl: './canvas.component.html',
  styleUrl: './canvas.component.scss'
})
export class CanvasComponent {

}
