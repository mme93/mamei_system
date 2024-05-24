import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ItemSetUp} from "../../../../../shared/model/dashboard/Item";


@Component({
  selector: 'app-item-setup',
  templateUrl: './item-setup.component.html',
  styleUrls: ['./item-setup.component.scss']
})
export class ItemSetupComponent{

  itemSetUp: ItemSetUp = {
    itemName: '',
    itemDescription: '',
    iconName: '',
    iconNames: ['delete', 'add', 'remove']
  }

  itemInformation = new FormGroup({
    itemName: new FormControl(this.itemSetUp.itemName, [Validators.required, Validators.minLength(1)]),
    itemDescription: new FormControl(this.itemSetUp.itemName, [Validators.required, Validators.minLength(1)]),
    itemIconName: new FormControl(this.itemSetUp.itemName, [Validators.required, Validators.minLength(1)])
  });


}
