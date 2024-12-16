import { Component, OnDestroy, OnInit } from '@angular/core';
import { PixelQuestComponentView } from './model/test';
import { ConfigService } from '../service/data/config/config.service';

@Component({
  selector: 'app-pixelquest',
  templateUrl: './pixelquest.component.html',
  styleUrl: './pixelquest.component.scss'
})
export class PixelquestComponent implements OnInit{

  view: PixelQuestComponentView = {
    isWorld: false,
    isLogin: true
  }

  constructor(private configService:ConfigService){}
 
  ngOnInit(): void {
    this.configService.componentView$.subscribe(view =>this.view=view);
  }

}
