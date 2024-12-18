import {Component, OnInit} from '@angular/core';
import {TitleEventService} from "../../../shared/event/title-event.service";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent implements OnInit{

  menuPoints = [
    {text: "Service Status",isSelected:true, title:'Micro Service Status'},
    {text: "Administration",isSelected:false, title:'Administration'},
    {text: "Account",isSelected:false, title:'Account'},
    {text: "UI-Settings",isSelected:false, title:'UI-Settings'}
  ]
  selectedMenu = '';

  constructor(private eventService: TitleEventService) {
  }

  ngOnInit(): void {
    this.selectedMenu = this.menuPoints[0].text;
    this.eventService.updateTitle('Settings - '+this.menuPoints[0].title)
    }

  changeUI(text: string) {
    for(let i=0;i<this.menuPoints.length;i++){
      if(text ===this.menuPoints[i].text){
        this.selectedMenu = text;
        this.menuPoints[i].isSelected=true;
        this.eventService.updateTitle('Settings - '+this.menuPoints[i].title)
      }else{
        this.menuPoints[i].isSelected=false;
      }
    }

  }
}
