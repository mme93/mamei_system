import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';
import { filter, Subscription } from 'rxjs';
import { PixelQuestAccountDto } from 'src/app/model/account';
import { AccountService } from 'src/app/service/data/account/account.service';
import { ScreenService } from 'src/app/service/tools/screen/screen.service';
import { PixelquestHeaderComponent } from "./pixelquest-header/pixelquest-header.component";
import { PixelElementComponent } from './pixel-element/pixel-element.component';

@Component({
  selector: 'app-pixel-content',
  standalone: true,
  imports: [MenubarModule, PixelquestHeaderComponent,PixelElementComponent],
  templateUrl: './pixel-content.component.html',
  styleUrl: './pixel-content.component.scss'
})
export class PixelContentComponent implements OnInit {

  account: PixelQuestAccountDto = {};
  screenSize: { width: number, height: number } | null = null;
  blockWidth: number = 0;
  blockHight: number = 0;
  headerHeight:number=0;
  private subscription!: Subscription;
  rows: number = 14;
  cols: number = 32;
  test=['Test'];
  constructor(private screenService: ScreenService, private accountService: AccountService) {

  }

  ngOnInit(): void {
    this.subscription = this.screenService.screenSize$.subscribe(size => {
      this.screenSize = {
        width: (size.width * 0.8),
        height: (size.height * 0.7)
      };
      this.headerHeight = (size.height * 0.05) ;
      this.blockHight = (size.height * 0.7) / this.rows;
      this.blockWidth = (size.width * 0.8) / this.cols;
    });

    this.accountService.account$
      .pipe(filter(account => account !== null))
      .subscribe(account => {
        if (account && account !== account) {
          this.account = account;
        }
      });
  }

}
