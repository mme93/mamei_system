import { Component } from '@angular/core';
import {AccountService} from "../../../../shared/services/help/account/account.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent {
  userProfile = {
    id: 1,
    call_number: '123-456-7890',
    email: 'example@example.com',
    first_name: 'John',
    last_name: 'Doe',
    role: 'User',
    user_id: 'johndoe123',
    username: 'johndoe'
  };

  isEditing = false;

  constructor(private service:AccountService) {
    service.loadAccount().subscribe(result => console.log(result));
  }

  startEditing(): void {
    this.isEditing = true;
  }

  saveChanges(): void {
    // Hier könntest du die Änderungen speichern (zum Beispiel an einen Server senden)
    this.isEditing = false;
  }
}
