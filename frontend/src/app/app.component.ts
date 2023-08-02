import {Component, HostListener, OnInit} from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  showIcon = false;

  constructor(private router: Router) {
    window.addEventListener('message', (event) => {
      if (event.data.type === 'updateLogoutButton') {
        if(event.data.value){
          this.showIcon = true;
        }
      }
    });
  }

  ngOnInit(): void {
    const loginValue = localStorage.getItem('login');
    if (loginValue === 'true') {
      this.showIcon = true;
    } else {
      this.showIcon = false;
    }
  }


  logout() {
    localStorage.setItem('login', 'false')
    this.showIcon = false;
    this.router.navigate(['/login'])
  }
}
