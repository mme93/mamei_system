import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { ScreenService } from 'src/app/service/tools/screen/screen.service';
import { PixelquestMapEditorElementComponent } from "./pixelquest-map-editor-element/pixelquest-map-editor-element.component";
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PixelquestMapEditorContentComponent } from './pixelquest-map-editor-content/pixelquest-map-editor-content.component';
import { PixelContentComponent } from "../pixel-content/pixel-content.component";

@Component({
  selector: 'app-pixelquest-map-editor',
  standalone: true,
  imports: [PixelquestMapEditorElementComponent, PixelquestMapEditorContentComponent, FormsModule, CommonModule, PixelContentComponent],
  templateUrl: './pixelquest-map-editor.component.html',
  styleUrl: './pixelquest-map-editor.component.scss'
})
export class PixelquestMapEditorComponent {

  rows: number = 32;
  cols: number = 50;
  title: string = 'New Map';

  dummy: String[][] = [];
  blockWidth: number = 0;
  blockHight: number = 0;

  screenSize: { width: number, height: number } | null = null;
  sideBarSize: { width: number, height: number } | null = null;
  private subscription!: Subscription;

  constructor(private screenService: ScreenService) {

  }

  ngOnInit(): void {
    this. updateGrid();
    this.subscription = this.screenService.screenSize$.subscribe(size => {
      this.screenSize = {
        width: (size.width * 0.7),
        height: (size.height * 0.6)
      };
      this.sideBarSize = {
        width: (size.width * 0.13),
        height: (size.height)
      };
      this.updateBlock();
    });
  }
  
  updateBlock(){
    if(this.screenSize?.height){
      this.blockHight = this.screenSize?.height / this.rows;
    }

    if(this.screenSize?.width){
      this.blockHight = this.screenSize?.width / this.cols;
    }
  }

  updateGrid() {
    this.dummy=[];
    for(let i:number=0;i<this.rows;i++){
      let dummyEle:String[]=[];
      for(let j:number=0;j<this.cols;j++){
       dummyEle.push('Test');
      }
      this.dummy.push(dummyEle);
    }
    this.updateBlock();
  }
}
