import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-pixelquest-map-editor-element',
  standalone: true,
  imports: [],
  templateUrl: './pixelquest-map-editor-element.component.html',
  styleUrl: './pixelquest-map-editor-element.component.scss'
})
export class PixelquestMapEditorElementComponent {


  @Input() newImage: string = '/assets/stone_ground_field.png';
  @Input() changeImage: boolean = false;
  @Input() height: number = 75;
  @Input() width: number = 75;
  image_path = '/assets/stone_ground_field.png'

}
