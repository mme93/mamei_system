import { Component } from '@angular/core';
import { PixelquestMapEditorElementComponent } from "../pixelquest-map-editor-element/pixelquest-map-editor-element.component";
import { Subscription } from 'rxjs';
import { ScreenService } from 'src/app/service/tools/screen/screen.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-pixelquest-map-editor-content',
  standalone: true,
  imports: [PixelquestMapEditorElementComponent,CommonModule],
  templateUrl: './pixelquest-map-editor-content.component.html',
  styleUrl: './pixelquest-map-editor-content.component.scss'
})
export class PixelquestMapEditorContentComponent {


rows: number = 10;
  cols: number = 15;
  title: string = 'New Map';

  dummy: String[][] = [];
  blockWidth: number = 0;
  blockHight: number = 0;

  screenSize: { width: number, height: number } | null = null;
  private subscription!: Subscription;

  constructor(private screenService: ScreenService) {

  }

  ngOnInit(): void {
    this.subscription = this.screenService.screenSize$.subscribe(size => {
      this.screenSize = {
        width: (size.width * 0.7),
        height: (size.height * 0.6)
      };
      this.blockWidth= (size.width * 0.7/this.cols);
      this.blockHight= (size.height * 0.6/this.rows);
    });


    for(let i:number=0;i<this.rows;i++){
      let x:String[]=[];
      for(let j:number=0;j<this.cols;j++){
        x.push('Test');
      }
      this.dummy.push(x)
      
    }
    console.log(this.dummy)
  }

}
