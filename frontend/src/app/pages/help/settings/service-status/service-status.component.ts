import {Component, OnInit} from '@angular/core';
import {MicroserviceStatusService} from "../../../../shared/services/help/settings/microservice-status.service";
import {MicroService} from "../../../../shared/model/MicroSerivce";

@Component({
  selector: 'app-service-status',
  templateUrl: './service-status.component.html',
  styleUrls: ['./service-status.component.scss']
})
export class ServiceStatusComponent implements OnInit{
  microServices:MicroService[]=[];
  serverStatusToolTip= "Tip";
  constructor(private microServiceStatus:MicroserviceStatusService) {
  }

  ngOnInit(): void {
    // @ts-ignore
    this.microServiceStatus.getMicroServiceStatus().subscribe((value:MicroService) =>this.microServices=value);
  }

}
