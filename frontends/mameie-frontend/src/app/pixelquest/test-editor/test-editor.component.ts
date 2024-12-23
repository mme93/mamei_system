import { Component, OnInit } from '@angular/core';
import { XElementComponent } from './x-element/x-element.component';
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';
import { ScreenService } from 'src/app/service/tools/screen/screen.service';
import { PixelquestHeaderComponent } from '../pixel-content/pixelquest-header/pixelquest-header.component';

@Component({
  selector: 'app-test-editor',
  standalone: true,
  imports: [XElementComponent, CommonModule,PixelquestHeaderComponent],
  templateUrl: './test-editor.component.html',
  styleUrl: './test-editor.component.scss'
})
export class TestEditorComponent implements OnInit {
  dummy: String[][] = [];
  blockWidth: number = 0;
  blockHight: number = 0;
  rows: number = 14;
  cols: number = 32;
  screenSize: { width: number, height: number } | null = null;
  private subscription!: Subscription;

  constructor(private screenSizeService: ScreenService) {

  }

  ngOnInit(): void {
    this.subscription = this.screenSizeService.screenSize$.subscribe(size => {
      this.screenSize = {
        width: (size.width * 0.8),
        height: (size.height * 0.7)
      };
      this.blockHight = (size.height * 0.7) / this.rows;
      this.blockWidth = (size.width * 0.8) / this.cols;
    });
    for(let i:number=0;i<this.rows;i++){
      let x:String[]=[];
      for(let j:number=0;j<this.cols;j++){
        x.push('Test');
      }
      this.dummy.push(x)
      
    }
  }

}
