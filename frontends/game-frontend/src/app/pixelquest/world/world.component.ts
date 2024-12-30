import { Component, OnDestroy, OnInit } from '@angular/core';
import { filter, Subscription } from 'rxjs';
import { ScreenSizeService } from '../../service/screen/screen-size.service';
import { MapService } from '../../service/data/map/map.service';
import { PixelQuestGridDto, PixelQuestMapDto } from '../model/test';
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
  blockWidth: number = 0;
  blockHight: number = 0;
  rows: number = 14;
  cols: number = 32;
  screenSize: { width: number, height: number } | null = null;
  private subscription!: Subscription;

  constructor(private pixelQuestAccountService: AccountService, private screenSizeService: ScreenSizeService, private mapService: MapService, private worldService: WorldService) {

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

    this.subscription = this.worldService.world$.subscribe(worlds => {
      const map = worlds?.maps.find(world => world.pixelQuestMap === this.pixelQuestAccountService.getAccount()?.pixelQuestMap);
      if (map) {
        this.map = map;
      }
    })

    this.subscription = this.mapService.map$
      .subscribe(map => {
        console.warn('Load Map: ', map);
        this.map = map!;
        this.grids = map?.grid ?? { rows: [] };
      });

  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
