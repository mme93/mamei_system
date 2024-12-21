import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AccountService } from 'src/app/service/data/account/account.service';
import { ScreenService } from 'src/app/service/tools/screen/screen.service';

@Component({
  selector: 'app-pixel-content',
  standalone: true,
  imports: [],
  templateUrl: './pixel-content.component.html',
  styleUrl: './pixel-content.component.scss'
})
export class PixelContentComponent implements OnInit {

  screenSize: { width: number, height: number } | null = null;
  blockWidth: number = 0;
  blockHight: number = 0;
  private subscription!: Subscription;
  rows: number = 14;
  cols: number = 32;

    constructor(private screenService: ScreenService, private accountService:AccountService){
      this.accountService.account$.subscribe(account =>{
        console.log(account)
      });
    }
  
    ngOnInit(): void {
      this.subscription = this.screenService.screenSize$.subscribe(size => {
        this.screenSize = {
          width: (size.width * 0.8),
          height: (size.height * 0.7)
        };
        this.blockHight = (size.height * 0.7) / this.rows;
        this.blockWidth = (size.width * 0.8) / this.cols;
      });

      this.accountService.account$.subscribe(account =>{
        console.log(account)
      });
      console.log(this.accountService.getAccount());

  }

}
