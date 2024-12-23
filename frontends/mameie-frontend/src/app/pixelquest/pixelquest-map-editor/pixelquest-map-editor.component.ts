import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { ScreenService } from 'src/app/service/tools/screen/screen.service';
import { PixelquestMapEditorElementComponent } from "./pixelquest-map-editor-element/pixelquest-map-editor-element.component";

@Component({
  selector: 'app-pixelquest-map-editor',
  standalone: true,
  imports: [PixelquestMapEditorElementComponent],
  templateUrl: './pixelquest-map-editor.component.html',
  styleUrl: './pixelquest-map-editor.component.scss'
})
export class PixelquestMapEditorComponent {

  screenSize: { width: number, height: number } | null = null;

  private subscription!: Subscription;

  constructor(private screenService: ScreenService) {

  }

  ngOnInit(): void {
    this.subscription = this.screenService.screenSize$.subscribe(size => {
      this.screenSize = {
        width: (size.width * 0.8),
        height: (size.height * 0.7)
      };
    });
  }
}
