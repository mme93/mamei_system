import { Component, OnInit } from '@angular/core';
import { MenuItem, Message, MessageService } from "primeng/api";
import { ErrorMessageService } from './pixelquest/service/message/error-message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'mameie-frontend';
 
  constructor(private errorMessageService: ErrorMessageService, private msgService: MessageService) { }

  ngOnInit(): void {
    this.errorMessageService.message$.subscribe((message) => {
      if (message) {
        this.msgService.add({
          severity: 'error',
          summary: 'Error: ',
          detail: message,
        });
        setTimeout(() => (this.msgService.clear()), 5000);
      }
    });
  }

}
