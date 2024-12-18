import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-pixel-login',
  standalone: true,
  imports: [CardModule,FormsModule],
  templateUrl: './pixel-login.component.html',
  styleUrl: './pixel-login.component.scss'
})
export class PixelLoginComponent {

  password = '';
  userName = '';

}
