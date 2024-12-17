import {Injectable} from '@angular/core';
import {MenuItem} from "primeng/api";

@Injectable({
  providedIn: 'root'
})
export class MenubarService {

  constructor() {
  }

  getMenubarItems(currentServerName: string): MenuItem[] {
    return [
      {
        label: 'Database Manager',
        routerLink: '/'
      },
      {
        label: 'Server',
        icon: 'pi pi-fw pi-server',
        items: [
          {
            label: 'Overview',
            icon: 'pi pi-fw pi-code',
            routerLink: '/dashboard/server/overview'
          },
          {
            label: 'Information',
            icon: 'pi pi-fw pi-info-circle',
            routerLink: '/dashboard/server/info'
          },
          {
            separator: true
          },
          {
            label: 'System Users',
            icon: 'pi pi-fw pi-user',
            routerLink: ['/system/user', 'test']
          },
          {
            separator: true
          },
          {
            label: 'Switch server',
            icon: 'pi pi-fw pi-chevron-circle-right',
            items: [
              {
                label: 'Cloud Server',
                icon: 'pi pi-fw pi-server'
              },
              {
                label: 'Cloud XXL',
                icon: 'pi pi-fw pi-server'
              },

            ]
          }
        ]
      },
      {
        label: 'Database',
        icon: 'pi pi-fw pi-database',
        routerLink: '/dashboard/database/overview'
      },
      {
        label: 'Table',
        icon: 'pi pi-fw pi-table',
        routerLink: '/dashboard/table'
      },
      {
        label: 'SQL',
        icon: 'pi pi-fw pi-send'
      }
    ];
  }
}
