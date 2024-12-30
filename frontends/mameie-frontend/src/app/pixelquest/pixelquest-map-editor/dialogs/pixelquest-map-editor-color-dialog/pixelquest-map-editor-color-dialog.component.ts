import { CommonModule } from '@angular/common';
import { Component, ElementRef, OnInit, AfterViewInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs';
import { NewMapImage } from 'src/app/pixelquest/model/config';
import { ScreenService } from 'src/app/pixelquest/service/tools/screen/screen.service';

@Component({
  selector: 'app-pixelquest-map-editor-color-dialog',
  standalone: true,
  imports: [CommonModule, InputTextModule, ButtonModule, DropdownModule, FormsModule],
  templateUrl: './pixelquest-map-editor-color-dialog.component.html',
  styleUrls: ['./pixelquest-map-editor-color-dialog.component.scss'],
})
export class PixelquestMapEditorColorDialogComponent implements OnInit, AfterViewInit {

  @ViewChild('dialogContent', { static: false }) dialogContent!: ElementRef<HTMLDivElement>;
  objectSize = 'normal';
  searchText = '';
  blockHeight: number = 0;
  blockwidth: number = 0;
  selectedImage: NewMapImage | null = null;

  images: NewMapImage[] = [];

  filteredImages: NewMapImage[] = [...this.images];
  private subscription!: Subscription;

  constructor(private ref: DynamicDialogRef, private screenSizeService: ScreenService, private cd: ChangeDetectorRef, public config: DynamicDialogConfig) {
    this.loadImages();
    this.filterImages();
  }

  ngOnInit(): void {
    this.subscription = this.screenSizeService.screenSize$.subscribe(() => {
      this.calculateDimensions();
    });
  }

  ngAfterViewInit(): void {
    this.calculateDimensions();
    this.cd.detectChanges();
    window.addEventListener('resize', () => this.calculateDimensions());
  }

  private calculateDimensions(): void {
    if (this.dialogContent) {
      const dialogWidth = this.dialogContent.nativeElement.offsetWidth;
      const padding = 20;
      const totalColumns = 4;

      this.blockwidth = (dialogWidth - padding * (totalColumns + 1)) / totalColumns;
      this.blockHeight = this.blockwidth;
    }
  }

  filterImages() {
    this.filteredImages = this.images.filter((image) =>
      !this.searchText || image.title.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  selectImage(image: NewMapImage) {
    this.selectedImage = image;
    this.images.forEach((img) => (img.isSelected = false));
    image.isSelected = true;
  }

  close(withValue: boolean) {
    if (withValue) {
      if (this.selectedImage && this.config.data === 'object') {
        this.selectedImage.objectSize = this.objectSize;
      }
      console.log(this.selectedImage)
      this.ref.close(this.selectedImage);
    } else {
      this.ref.close();
    }
  }

  loadImages() {
    this.images = [
      { title: 'Stone', src: '/assets/stone_ground_field.png', category: 'field', isSelected: false, size: 'normal' },
      { title: 'Wood', src: '/assets/fields/wood.png', category: 'field', isSelected: false, size: 'normal' },
      { title: 'Gras', src: '/assets/fields/gras.jpg', category: 'field', isSelected: false, size: 'normal' },
      { title: 'Sand', src: '/assets/fields/sand.jpg', category: 'field', isSelected: false, size: 'normal' },
      { title: 'Bon fire', src: '/assets/objects/bonfire.png', category: 'object', isSelected: false, size: this.objectSize },
      { title: 'Quest', src: '/assets/objects/quest_icon.png', category: 'object', isSelected: false, size: this.objectSize },
    ].filter(image => image.category === this.config.data);
  }

}