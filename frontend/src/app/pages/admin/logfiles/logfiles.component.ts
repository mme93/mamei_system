import {Component} from '@angular/core';
import {LogfilesService} from "../../../shared/services/admin/logfiles/logfiles.service";

@Component({
  selector: 'app-logfiles',
  templateUrl: './logfiles.component.html',
  styleUrls: ['./logfiles.component.scss']
})
export class LogfilesComponent {

  constructor(private logfilesService: LogfilesService) {
  }

  downloadInfoLog() {
    this.logfilesService.downloadInfoLog().subscribe((data: Blob) => {
      const downloadLink = document.createElement('a');
      const url = window.URL.createObjectURL(data);
      downloadLink.href = url;
      downloadLink.download = 'info.log';
      document.body.appendChild(downloadLink);
      downloadLink.click();
      setTimeout(() => {
        document.body.removeChild(downloadLink);
        window.URL.revokeObjectURL(url);
      }, 100);
    });
  }
}
