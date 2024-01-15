import {Component} from '@angular/core';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent {
  menuPoints = [
    {text: "Service Status"},
    {text: "Administration (ADMIN)"},
    {text: "Account"}
  ]
  selectedMenu = this.menuPoints[0].text;

  changeUI(text: string) {
    this.selectedMenu = text;
  }
}
