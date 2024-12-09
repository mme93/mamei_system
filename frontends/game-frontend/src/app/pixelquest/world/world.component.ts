import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ScreenSizeService } from '../../service/screen/screen-size.service';

@Component({
  selector: 'app-world',
  templateUrl: './world.component.html',
  styleUrls: ['./world.component.scss']
})
export class WorldComponent implements OnInit, OnDestroy {

  example:String[][]=[];
  blockWidth:number=0;
  blockHight:number=0;
  rows:number=14;
  cols:number=32;
  screenSize: { width: number, height: number } | null = null;
  private subscription!: Subscription;

  constructor(private screenSizeService: ScreenSizeService) {}

  ngOnInit(): void {
    this.subscription = this.screenSizeService.screenSize$.subscribe(size => {
      this.screenSize = {
        width: (size.width * 0.7),
        height: (size.height * 0.7)
      };
      this.blockHight=(size.height * 0.7)/this.rows;
      this.blockWidth=(size.width * 0.7)/this.cols;
    });
    this.createGrid();
  }

  createGrid(){
    for(let i=0;i<this.rows;i++){
      let x:String[]=[];
      for(let j=0;j<this.cols;j++){
        x.push(String(((i*10)+j+1)))
      }
      this.example.push(x);
    }
  
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
