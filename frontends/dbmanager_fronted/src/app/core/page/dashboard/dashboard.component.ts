import {Component, OnInit} from '@angular/core';
import {DashboardOverviewService} from "../../../shared/service/ui/dashboard/dashboard-overview.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  items: { name: string; iconName: string; route: string; }[] = [];

  constructor(private service: DashboardOverviewService, private router: Router) {
  }

  ngOnInit(): void {
    this.items = this.service.getDashboardOverviewItems();
  }

  navigate(route: string) {
    this.router.navigate([route]);
  }
}
