import {Component} from '@angular/core';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent {
  menuPoints = [
    {text: "Service Status",isSelected:true},
    {text: "Administration (ADMIN)",isSelected:false},
    {text: "Account",isSelected:false}
  ]
  selectedMenu = this.menuPoints[0].text;

  changeUI(text: string) {
    for(let i=0;i<this.menuPoints.length;i++){
      if(text ===this.menuPoints[i].text){
        this.selectedMenu = text;
        this.menuPoints[i].isSelected=true;
      }else{
        this.menuPoints[i].isSelected=false;
      }
    }

  }
}
