import { Component, OnInit } from '@angular/core';
import { MenuItem, Message } from "primeng/api";
import { ErrorMessageService } from './service/message/error-message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'mameie-frontend';
  messages: Message[] = [{
    severity: 'error',
    summary: 'Error',
    detail: 'blablabla123'
  }, {
    severity: 'error',
    summary: 'Error',
    detail: 'blablabla456'
  }, {
    severity: 'error',
    summary: 'Error',
    detail: 'blablabla789'
  }];
  
  globalMessage: string | null = null;

  constructor(private errorMessageService: ErrorMessageService) { }

  ngOnInit(): void {
    this.errorMessageService.message$.subscribe((message) => {
      this.globalMessage = message;
      if (message) {
        this.messages.push({
          severity: 'error',
          summary: message,
          sticky: true
        })
        setTimeout(() => (console.log(this.messages)), 5000);
      }
    });
  }

}
