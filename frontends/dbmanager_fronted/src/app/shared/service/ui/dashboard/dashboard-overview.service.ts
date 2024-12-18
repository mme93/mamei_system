import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DashboardOverviewService {

  constructor() {
  }

  getDashboardOverviewItems() {
    return [
      {
        name: 'Change Server',
        iconName: 'pi pi-server',
        route:'/dashboard/server'
      },
      {
        name: 'Server Information',
        iconName: 'pi pi-pencil',
        route:'/dashboard/server'
      },
      {
        name: 'Database Overview',
        iconName: 'pi pi-server',
        route:'/dashboard/server'
      },
      {
        name: 'Table - Create',
        iconName: 'pi pi-server',
        route:'/dashboard/server'
      },
      {
        name: 'SQL',
        iconName: 'pi pi-server',
        route:'/dashboard/server'
      },
      {
        name: 'SQL',
        iconName: 'pi pi-server',
        route:'/dashboard/server'
      },
      {
        name: 'SQL',
        iconName: 'pi pi-server',
        route:'/dashboard/server'
      },
      {
        name: 'SQL',
        iconName: 'pi pi-server',
        route:'/dashboard/server'
      },
      {
        name: 'SQL',
        iconName: 'pi pi-server',
        route:'/dashboard/server'
      }
    ];
  }

}
