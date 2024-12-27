import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { DynamicDialogRef } from 'primeng/dynamicdialog';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import { NewMapImage } from 'src/app/model/config';
import { ScreenService } from 'src/app/service/tools/screen/screen.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-pixelquest-map-editor-color-dialog',
  standalone: true,
  imports: [CommonModule, InputTextModule, ButtonModule, DropdownModule, FormsModule],
  templateUrl: './pixelquest-map-editor-color-dialog.component.html',
  styleUrl: './pixelquest-map-editor-color-dialog.component.scss'
})
export class PixelquestMapEditorColorDialogComponent implements OnInit {
  searchText = '';
  blockHeight: number = 0;
  blockwidth: number = 0;
  gridHeight: number = 0;
  gridwidth: number = 0;
  selectedImage: NewMapImage | null = null;
  categories = [
    { label: 'Fields', value: 'fields' },
    { label: 'Objects', value: 'objects' },
  ];

  images: NewMapImage[] = [
    { title: 'Stone', src: '/assets/stone_ground_field.png', category: 'fields', isSelected: false },
    { title: 'Wood', src: '/assets/fields/wood.png', category: 'fields', isSelected: false },
    { title: 'Gras', src: '/assets/fields/gras.jpg', category: 'fields', isSelected: false },
    { title: 'Sand', src: '/assets/fields/sand.jpg', category: 'fields', isSelected: false },

    { title: 'Stone', src: '/assets/stone_ground_field.png', category: 'fields', isSelected: false },
    { title: 'Wood', src: '/assets/fields/wood.png', category: 'fields', isSelected: false },
    { title: 'Gras', src: '/assets/fields/gras.jpg', category: 'fields', isSelected: false },
    { title: 'Sand', src: '/assets/fields/sand.jpg', category: 'fields', isSelected: false },
    { title: 'Stone', src: '/assets/stone_ground_field.png', category: 'fields', isSelected: false },
    { title: 'Wood', src: '/assets/fields/wood.png', category: 'fields', isSelected: false },
    { title: 'Gras', src: '/assets/fields/gras.jpg', category: 'fields', isSelected: false },
    { title: 'Sand', src: '/assets/fields/sand.jpg', category: 'fields', isSelected: false },
    { title: 'Stone', src: '/assets/stone_ground_field.png', category: 'fields', isSelected: false },
    { title: 'Wood', src: '/assets/fields/wood.png', category: 'fields', isSelected: false },
    { title: 'Gras', src: '/assets/fields/gras.jpg', category: 'fields', isSelected: false },
    { title: 'Sand', src: '/assets/fields/sand.jpg', category: 'fields', isSelected: false },

    { title: 'Object 2', src: '/assets/objects/bonfire.png', category: 'objects', isSelected: false },
    { title: 'Object 2', src: '/assets/objects/quest_icon.png', category: 'objects', isSelected: false },
  ];

  filteredImages: NewMapImage[] = [...this.images];
  private subscription!: Subscription;

  constructor(private ref: DynamicDialogRef, private screenSizeService: ScreenService) {
    this.filterImages();
  }
  ngOnInit(): void {
    this.subscription = this.screenSizeService.screenSize$.subscribe(size => {
      this.blockHeight = size.width / 5;
      this.blockwidth = size.width / 5;
      this.gridHeight = size.height;
      this.gridwidth = size.width;
    });
  }

  filterImages() {
    this.filteredImages = this.images.filter((image) => {
      const matchesSearch =
        !this.searchText ||
        image.title.toLowerCase().includes(this.searchText.toLowerCase());
      return matchesSearch;
    });
  }

  selectImage(image: NewMapImage) {
    this.selectedImage = image;
    this.images.forEach(image => image.isSelected = false);
    image.isSelected = true;
  }

  close(withValue: boolean) {
    if (withValue) {
      this.ref.close(this.selectedImage);
    } else {
      this.ref.close();
    }
  }
}
