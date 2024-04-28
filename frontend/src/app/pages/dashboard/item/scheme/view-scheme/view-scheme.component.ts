import {Component, Input} from '@angular/core';
import {StandardComponentSetUp} from "../../../../../shared/model/dashboard/Components";

@Component({
  selector: 'app-view-scheme',
  templateUrl: './view-scheme.component.html',
  styleUrls: ['./view-scheme.component.scss']
})
export class ViewSchemeComponent {
  @Input() data: StandardComponentSetUp[] = [];
}
