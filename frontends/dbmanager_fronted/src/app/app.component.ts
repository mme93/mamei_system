import {Component} from '@angular/core';
import {MenuItem} from "primeng/api";
import {MenubarService} from "./shared/service/ui/menubar/menubar.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'dbmanager_fronted';
  items: MenuItem[] = [];
  isMenubarDisabled=false;

  constructor(private menubarService:MenubarService,private router:Router) {
  }

  ngOnInit() {
    this.loadItems();
  }

  loadItems(){
    this.items = this.menubarService.getMenubarItems("Cloud XXL");
    this.isMenubarDisabled = false;
  }

  logout() {
    localStorage.removeItem('server')
    this.isMenubarDisabled = true;
    this.items=[];
    this.router.navigate(['/login']);
  }
}
