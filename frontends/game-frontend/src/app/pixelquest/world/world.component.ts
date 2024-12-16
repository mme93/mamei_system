import { Component, OnDestroy, OnInit } from '@angular/core';
import { filter, Subscription } from 'rxjs';
import { ScreenSizeService } from '../../service/screen/screen-size.service';
import { WorldGridElement, WorldGridRow } from '../model/pixelquestmodel';
import { MapService } from '../../service/data/map/map.service';
import { PixelQuestGridDto, PixelQuestMapDto } from '../model/test';
import { TestService } from '../../service/data/config/test.service';
import { WorldService } from '../../service/data/world/world.service';
import { AccountService } from '../../service/data/account/account.service';

@Component({
  selector: 'app-world',
  templateUrl: './world.component.html',
  styleUrls: ['./world.component.scss']
})
export class WorldComponent implements OnInit, OnDestroy {

  map: PixelQuestMapDto = {
    height: 0,
    width: 0,
    pixelQuestMap: '',
    grid: { rows: [] }
  }
  grids: PixelQuestGridDto = {
    rows: []
  };
  grid: WorldGridRow[] = [];
  example: String[][] = [];
  blockWidth: number = 0;
  blockHight: number = 0;
  rowStartPoint = 7;
  colStartPoint = 16;
  rows: number = 14;
  cols: number = 32;
  screenSize: { width: number, height: number } | null = null;
  private subscription!: Subscription;

  constructor(private accountService:AccountService,private screenSizeService: ScreenSizeService, private mapService: MapService, private worldService: WorldService, private testService: TestService) {

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

    this.subscription = this.worldService.world$.subscribe(world => {
      const x=world?.maps.find(world => world.pixelQuestMap===this.accountService.getAccount()?.pixelQuestMap);
      console.log(x)
    })

    this.subscription = this.testService.test$.subscribe(test => {
      console.warn(test);
      console.warn(this.mapService.getMap())
    })

    this.subscription = this.mapService.map$
      .subscribe(map => {
        console.warn('Load Map: ', map);
        this.map = map!;
        this.grids = map?.grid ?? { rows: [] };
      });
    this.createGrid();

  }


  createGrid() {
    for (let i = 0; i < this.rows; i++) {
      let x: String[] = [];
      let row: WorldGridRow = { gridElements: [] };
      let isStart = false;
      for (let j = 0; j < this.cols; j++) {
        x.push(String(((i * 10) + j + 1)))
        isStart = (j === this.colStartPoint && i == this.rowStartPoint);
        let backgroundImage: string = isStart ? './assets/fields/wood.png' : './assets/stone_ground_field.png';
        row.gridElements.push({
          rowSize: this.rows,
          colSize: this.cols,
          rowIndex: i,
          colIndex: j,
          backgroundImg: backgroundImage,
          hasPerson: isStart
        } as WorldGridElement)

      }
      this.example.push(x);

      this.grid.push(row);
    }

  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
