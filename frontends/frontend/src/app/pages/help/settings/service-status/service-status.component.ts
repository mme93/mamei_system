import {Component, OnInit} from '@angular/core';
import {MicroserviceStatusService} from "../../../../shared/services/help/settings/microservice-status.service";
import {MicroService} from "../../../../shared/model/MicroSerivce";

@Component({
  selector: 'app-service-status',
  templateUrl: './service-status.component.html',
  styleUrls: ['./service-status.component.scss']
})
export class ServiceStatusComponent implements OnInit{
  copyMicroServices:MicroService[]=[];
  microServices:MicroService[]=[];
  startStatus='OFFLINE';
  sortByStatusDisabled=false;
  statusIconList=[
    {icon_name:'all_inclusive', serviceStatus:'ALL',text:'Show all Services'},
    {icon_name:'signal_cellular_null', serviceStatus:'ONLINE',text:'Show online Services'},
    {icon_name:'signal_cellular_nodata', serviceStatus:'OFFLINE',text:'Show offline Services'}
  ];
  serverStatusToolTip= "Tip";
  status_icon=this.statusIconList[0].icon_name;
  status_text=this.statusIconList[0].text;
  constructor(private microServiceStatus:MicroserviceStatusService) {

  }

  ngOnInit(): void {
    // @ts-ignore
    this.microServiceStatus.getMicroServiceStatus().subscribe((value: MicroService[]) => {
      this.microServices=value;
      this.copyMicroServices=value;
    });
  }
  sortByName(){
    this.microServices=this.microServices.sort((a, b) => {
      const textA = a.text.toUpperCase();
      const textB = b.text.toUpperCase();

      if (textA < textB) {
        return -1;
      }

      if (textA > textB) {
        return 1;
      }

      return 0;
    });
  }
  sortByStatus(){
    this.microServices=[];
    if(this.startStatus==='OFFLINE'){
     for(let j=0;j<2;j++){
       for(let i=0;i<this.copyMicroServices.length;i++){
          if(j===0){
            if(this.copyMicroServices[i].status==='ONLINE'){
              this.microServices.push(this.copyMicroServices[i]);
            }
          }else if(j===1){
            if(this.copyMicroServices[i].status==='OFFLINE'){
              this.microServices.push(this.copyMicroServices[i]);
            }
          }
       }
     }
      this.startStatus='ONLINE'
    }else if(this.startStatus==='ONLINE'){
      for(let j=0;j<2;j++){
        for(let i=0;i<this.copyMicroServices.length;i++){
          if(j===0){
            if(this.copyMicroServices[i].status==='OFFLINE'){
              this.microServices.push(this.copyMicroServices[i]);
            }
          }else if(j===1){
            if(this.copyMicroServices[i].status==='ONLINE'){
              this.microServices.push(this.copyMicroServices[i]);
            }
          }
        }
      }
      this.startStatus='OFFLINE'
    }
  }
  filterServices(){
    this.microServices=[];
    if(this.status_icon===this.statusIconList[0].icon_name){
      for(let i=0;i<this.copyMicroServices.length;i++){
        if(this.copyMicroServices[i].status==='ONLINE'){
          this.microServices.push(this.copyMicroServices[i]);
          this.sortByStatusDisabled=true;
        }
      }
      this.changeButtonWithLabel(1);
    }else if(this.status_icon===this.statusIconList[1].icon_name){
      for(let i=0;i<this.copyMicroServices.length;i++){
        if(this.copyMicroServices[i].status==='OFFLINE'){
          this.microServices.push(this.copyMicroServices[i]);
          this.sortByStatusDisabled=true;
        }
      }
      this.changeButtonWithLabel(2);
    }else if(this.status_icon===this.statusIconList[2].icon_name){
      this.microServices=this.copyMicroServices;
      this.changeButtonWithLabel(0);
      this.sortByStatusDisabled=false;
    }

  }
  changeButtonWithLabel(index:number){
    this.status_icon=this.statusIconList[index].icon_name
    this.status_text=this.statusIconList[index].text;
  }

}
