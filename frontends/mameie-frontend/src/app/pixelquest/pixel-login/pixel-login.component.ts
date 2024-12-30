import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { Subscription } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AccountService } from '../service/data/account/account.service';
import { ScreenService } from '../service/tools/screen/screen.service';

@Component({
  selector: 'app-pixel-login',
  standalone: true,
  imports: [CardModule, FormsModule, ButtonModule, FormsModule, ReactiveFormsModule, PanelModule],
  templateUrl: './pixel-login.component.html',
  styleUrl: './pixel-login.component.scss'
})
export class PixelLoginComponent {


  loginForm: FormGroup = new FormGroup({
    'login': new FormControl('', Validators.required),
    'password': new FormControl('', Validators.required)
  });

  submitted = false;

  password = 'Admin';
  userName = 'Admin';

  screenSize: { width: number, height: number } | null = null;
  private subscription!: Subscription;

  constructor(private screenService: ScreenService, private router: Router, private pixelQuestAccountService: AccountService) { }

  ngOnInit(): void {
    this.subscription = this.screenService.screenSize$.subscribe(size => {
      this.screenSize = {
        width: (size.width * 0.4),
        height: (size.height * 0.8)
      };
    });
  }

  login() {
    this.pixelQuestAccountService.login({
      userName: this.userName,
      password: this.password
    }).subscribe((response: boolean) => {
      if (response) {
        this.router.navigate(['pixelquest']);
      }
    });
  }

}
