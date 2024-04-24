import {Component, Input, OnInit} from '@angular/core';
import {SchemeList} from "../../create-item/create-item.component";

@Component({
  selector: 'app-view-scheme',
  templateUrl: './view-scheme.component.html',
  styleUrls: ['./view-scheme.component.scss']
})
export class ViewSchemeComponent implements OnInit{
  @Input() data: SchemeList[] = [];

  ngOnInit(): void {
    console.log(this.data)
  }

  printData() {
    console.log(this.data)
  }
}
