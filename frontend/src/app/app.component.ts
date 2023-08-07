import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  isSidenavDisabled: boolean = true;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }


  logout() {
    localStorage.setItem('token', '')
    this.isSidenavDisabled = false;
    this.router.navigate(['/login'])
  }
}
