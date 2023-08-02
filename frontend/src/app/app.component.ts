import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  showIcon = false;

  ngOnInit(): void {
    const loginValue = localStorage.getItem('login');
    if (loginValue === 'true') {
      this.showIcon = true;
    } else {
      this.showIcon = false;
    }
  }


  login() {
    localStorage.setItem('login', 'false')
    this.showIcon = false;
  }

}
