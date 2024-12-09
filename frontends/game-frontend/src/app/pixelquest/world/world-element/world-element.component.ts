import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-world-element',
  templateUrl: './world-element.component.html',
  styleUrl: './world-element.component.scss'
})
export class WorldElementComponent {
  @Input() image_path: string = ''; 
  @Input() height: number = 75;
  @Input() width: number = 75;
  @Input() hasFigure: boolean=false;
  @Input() hasObject: boolean=false;
  @Input() figurePath: string = ''; 
  @Input() objectPath: string = ''; 

}
