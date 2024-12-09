import { Component, Input } from '@angular/core';
import { FigurecontrollerService } from '../../../service/data/figure/figurecontroller.service';

@Component({
  selector: 'app-world-element',
  templateUrl: './world-element.component.html',
  styleUrl: './world-element.component.scss'
})
export class WorldElementComponent {
  @Input() image_path: string = ''; 
  @Input() height: number = 75;
  @Input() width: number = 75;
  @Input() col_pos: number = 0;
  @Input() row_pos: number = 0;
  @Input() hasFigure: boolean=false;
  @Input() hasObject: boolean=false;
  @Input() figurePath: string = ''; 
  @Input() objectPath: string = ''; 

  constructor(public figurecontrollerService:FigurecontrollerService){
    figurecontrollerService.figure$.subscribe(figure =>{
      this.hasFigure=this.col_pos===figure.col_pos && this.row_pos===figure.row_pos;
    })
  }

}
