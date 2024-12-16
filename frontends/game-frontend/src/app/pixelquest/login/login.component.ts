import { Component } from '@angular/core';
import { ScreenSizeService } from '../../service/screen/screen-size.service';
import { Subscription } from 'rxjs';
import { ConfigService } from '../../service/data/config/config.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  screenSize: { width: number, height: number } | null = null;
  private subscription!: Subscription;

  constructor(private screenSizeService: ScreenSizeService,private configService:ConfigService) { }

  ngOnInit(): void {
    this.subscription = this.screenSizeService.screenSize$.subscribe(size => {
      this.screenSize = {
        width: (size.width * 0.8),
        height: (size.height * 0.7)
      };
    });
  }

  login(){
    this.configService.emitView({isWorld:true,isLogin:false});
  }
}
