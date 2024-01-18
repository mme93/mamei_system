import {Component} from '@angular/core';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent {

  menuPoints = [
    {text: "Service Status",isSelected:true, title:'Micro Service Status'},
    {text: "Administration (ADMIN)",isSelected:false, title:'Administration Services and Rights'},
    {text: "Account",isSelected:false, title:'Account'},
    {text: "UI-Settings",isSelected:false, title:'UI-Settings'}
  ]
  selectedTitle= this.menuPoints[0].title;
  selectedMenu = this.menuPoints[0].text;

  changeUI(text: string) {
    for(let i=0;i<this.menuPoints.length;i++){
      if(text ===this.menuPoints[i].text){
        this.selectedMenu = text;
        this.menuPoints[i].isSelected=true;
        this.selectedTitle= this.menuPoints[i].title;
      }else{
        this.menuPoints[i].isSelected=false;
      }
    }

  }
}
