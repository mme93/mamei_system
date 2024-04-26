import {Component, Input} from '@angular/core';
import {SchemeList} from "../../create-item/create-item.component";

@Component({
  selector: 'app-view-scheme',
  templateUrl: './view-scheme.component.html',
  styleUrls: ['./view-scheme.component.scss']
})
export class ViewSchemeComponent {
  @Input() data: SchemeList[] = [];
}
