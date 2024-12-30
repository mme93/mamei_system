import { Component } from '@angular/core';
import { CanvasContentComponent } from "./canvas-content/canvas-content.component";
import { CanvasDeviceComponent } from "./canvas-device/canvas-device.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-canvas',
  standalone: true,
  imports: [CanvasContentComponent, CanvasDeviceComponent,CommonModule],
  templateUrl: './canvas.component.html',
  styleUrl: './canvas.component.scss'
})
export class CanvasComponent {

  isMobile(): boolean {
    const userAgent = navigator.userAgent || navigator.vendor || (window as any).opera;
    return /android|webos|iphone|ipad|ipod|blackberry|iemobile|opera mini/i.test(userAgent.toLowerCase());
  }
  
  isTablet(): boolean {
    const userAgent = navigator.userAgent || navigator.vendor || (window as any).opera;
    return /ipad|tablet|playbook|silk/i.test(userAgent.toLowerCase());
  }

}
