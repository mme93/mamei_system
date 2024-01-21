import { Injectable } from '@angular/core';

export interface DatabaseProcess {
  id: number;
  processLabel: string;
  processIcon: string;
  processDescription: string;
  processActivated: boolean;
  processIsShowActivated: boolean;
  processStatusIcon: string;
}

@Injectable({
  providedIn: 'root'
})
export class DatabaseProcessService {

  constructor() { }

  getDataBaseProcess(processStatusIcon:string[]){
    return [
      {
        id: 1,
        processLabel: 'BackUp Database-Data',
        processIcon: 'info',
        processDescription: 'Create a BackUp for this Database.',
        processActivated: false,
        processIsShowActivated: true,
        processStatusIcon: processStatusIcon[0]
      }, {
        id: 2,
        processLabel: 'Delete Database mamei_system',
        processIcon: 'info',
        processDescription: 'Delete Database with all tables and data.',
        processActivated: false,
        processIsShowActivated: true,
        processStatusIcon: processStatusIcon[0]
      }, {
        id: 3,
        processLabel: 'Create Database mamei_system',
        processIcon: 'info',
        processDescription: 'Restart all Micro-Services, to create Tables.',
        processActivated: false,
        processIsShowActivated: true,
        processStatusIcon: processStatusIcon[0]
      }, {
        id: 4,
        processLabel: 'Restart all needed Micro-Services and create Table',
        processIcon: 'info',
        processDescription: 'Restart all Micro-Services, to create Tables.',
        processActivated: false,
        processIsShowActivated: true,
        processStatusIcon: processStatusIcon[0]
      }, {
        id: 5,
        processLabel: 'Set Default Dataset',
        processIcon: 'info',
        processDescription: 'Create default data in different tables, which needed for the system.',
        processActivated: false,
        processIsShowActivated: true,
        processStatusIcon: processStatusIcon[0]
      }
    ];
  }

}
