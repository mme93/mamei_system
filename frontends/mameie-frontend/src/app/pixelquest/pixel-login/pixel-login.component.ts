import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { Subscription } from 'rxjs';
import { ScreenService } from 'src/app/service/tools/screen/screen.service';
import { PanelModule } from 'primeng/panel';
import { Router } from '@angular/router';
import { AccountService } from 'src/app/service/data/account/account.service';

@Component({
  selector: 'app-pixel-login',
  standalone: true,
  imports: [CardModule, FormsModule, ButtonModule, FormsModule, ReactiveFormsModule, PanelModule],
  providers: [AccountService],
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
    });
    //this.router.navigate(['pixelquest']);
  }

}
