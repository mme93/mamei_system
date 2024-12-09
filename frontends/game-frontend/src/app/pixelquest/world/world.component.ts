import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ScreenSizeService } from '../../service/screen/screen-size.service';

@Component({
  selector: 'app-world',
  templateUrl: './world.component.html',
  styleUrls: ['./world.component.scss']
})
export class WorldComponent implements OnInit, OnDestroy {

  screenSize: { width: number, height: number } | null = null;
  private subscription!: Subscription;

  constructor(private screenSizeService: ScreenSizeService) {}

  ngOnInit(): void {
    this.subscription = this.screenSizeService.screenSize$.subscribe(size => {
      this.screenSize = {
        width: (size.width * 0.7),
        height: (size.height * 0.7)
      };
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
