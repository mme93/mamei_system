import { Component } from '@angular/core';

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

  startEditing(): void {
    this.isEditing = true;
  }

  saveChanges(): void {
    // Hier könntest du die Änderungen speichern (zum Beispiel an einen Server senden)
    this.isEditing = false;
  }
}
