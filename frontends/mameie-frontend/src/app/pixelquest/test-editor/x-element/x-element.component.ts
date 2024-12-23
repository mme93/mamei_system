import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-x-element',
  standalone: true,
  imports: [],
  templateUrl: './x-element.component.html',
  styleUrl: './x-element.component.scss'
})
export class XElementComponent {
  @Input() height: number = 75;
  @Input() width: number = 75;
  image_path='/assets/stone_ground_field.png'

  constructor(){
    console.log(this.height)
  }

}
