import {Component} from '@angular/core';
import {AccountInfo, AccountService} from "../../../../shared/services/help/account/account.service";
import {TitleEventService} from "../../../../shared/event/title-event.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent {

  userProfile: AccountInfo = {
    id: 0,
    userId: 0,
    firstName: "",
    lastName: "",
    username: "",
    callNumber: "",
    email: "",
    role: "",
    microServices: ""
  }

  accountFromGroup = new FormGroup({
    firstName: new FormControl(this.userProfile.firstName, [Validators.required, Validators.minLength(1)]),
    lastName: new FormControl(this.userProfile.lastName, [Validators.required, Validators.minLength(1)]),
    username: new FormControl(this.userProfile.username, [Validators.required, Validators.minLength(1)]),
    callNumber: new FormControl(this.userProfile.callNumber, [Validators.required, Validators.minLength(1)]),
    email: new FormControl(this.userProfile.email, [Validators.required, Validators.minLength(1)])
  });

  isEditing = false;

  constructor(private service: AccountService,private eventService: TitleEventService
  ) {
    service.loadAccount().subscribe(result => this.userProfile = result);
  }

  ngOnInit(): void {
    this.eventService.updateTitle('Account Information')
  }

  startEditing(): void {
    this.isEditing = true;
  }

  saveChanges(): void {
    // Hier könntest du die Änderungen speichern (zum Beispiel an einen Server senden)
    this.isEditing = false;
  }

  getErrorMessage() {

  }
}
