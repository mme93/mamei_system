import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-pixel-element',
  standalone: true,
  imports: [],
  templateUrl: './pixel-element.component.html',
  styleUrl: './pixel-element.component.scss'
})
export class PixelElementComponent {
  @Input() image_path: string = ''; 
  @Input() height: number = 75;
  @Input() width: number = 75;

}
