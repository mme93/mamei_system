import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'mameie-frontend';
  items: MenuItem[]=[];

  ngOnInit(): void {
    this.items = [
      {
        label: 'File',
        items: [
          { label: 'New', icon: 'pi pi-fw pi-plus', command: () => this.onNewFile() },
          { label: 'Open', icon: 'pi pi-fw pi-download' },
          { separator: true },
          { label: 'Quit', icon: 'pi pi-fw pi-times' }
        ]
      },
      {
        label: 'Edit',
        items: [
          { label: 'Undo', icon: 'pi pi-fw pi-undo' },
          { label: 'Redo', icon: 'pi pi-fw pi-repeat' }
        ]
      },
      {
        label: 'Help',
        items: [
          { label: 'Contents', icon: 'pi pi-fw pi-file' },
          { label: 'Search', icon: 'pi pi-fw pi-search' }
        ]
      },
      {
        label: 'Actions',
        items: [
          { label: 'Edit', icon: 'pi pi-fw pi-pencil' },
          { label: 'Delete', icon: 'pi pi-fw pi-trash' },
          { label: 'Archive', icon: 'pi pi-fw pi-archive' }
        ]
      },
      {
        label: 'Quit', icon: 'pi pi-fw pi-power-off'
      }
    ];
  }

  onNewFile() {
    // Handle new file action
  }

}
