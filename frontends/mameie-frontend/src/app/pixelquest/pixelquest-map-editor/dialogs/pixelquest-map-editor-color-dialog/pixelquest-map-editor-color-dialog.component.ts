import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { DynamicDialogRef } from 'primeng/dynamicdialog';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';

export interface X {
  title: String;
  src: String;
  category: String;
  isSelected:boolean;
}

@Component({
  selector: 'app-pixelquest-map-editor-color-dialog',
  standalone: true,
  imports: [CommonModule, InputTextModule, ButtonModule, DropdownModule, FormsModule],
  templateUrl: './pixelquest-map-editor-color-dialog.component.html',
  styleUrl: './pixelquest-map-editor-color-dialog.component.scss'
})
export class PixelquestMapEditorColorDialogComponent {
  searchText = '';
  selectedCategory: string = '';
  selectedImage: X | null = null;
  categories = [
    { label: 'Fields', value: 'fields' },
    { label: 'Objects', value: 'objects' },
  ];

  images: X[] = [
    { title: 'Stone', src: '/assets/stone_ground_field.png', category: 'fields',isSelected:false },
    { title: 'Wood', src: '/assets/fields/wood.png', category: 'fields' ,isSelected:false},
    { title: 'Gras', src: '/assets/fields/gras.jpg', category: 'fields',isSelected:false },
    { title: 'Sand', src: '/assets/fields/sand.jpg', category: 'fields',isSelected:false },

    { title: 'Stone', src: '/assets/stone_ground_field.png', category: 'fields',isSelected:false },
    { title: 'Wood', src: '/assets/fields/wood.png', category: 'fields' ,isSelected:false},
    { title: 'Gras', src: '/assets/fields/gras.jpg', category: 'fields',isSelected:false },
    { title: 'Sand', src: '/assets/fields/sand.jpg', category: 'fields',isSelected:false },
    { title: 'Stone', src: '/assets/stone_ground_field.png', category: 'fields',isSelected:false },
    { title: 'Wood', src: '/assets/fields/wood.png', category: 'fields' ,isSelected:false},
    { title: 'Gras', src: '/assets/fields/gras.jpg', category: 'fields',isSelected:false },
    { title: 'Sand', src: '/assets/fields/sand.jpg', category: 'fields',isSelected:false },
    { title: 'Stone', src: '/assets/stone_ground_field.png', category: 'fields',isSelected:false },
    { title: 'Wood', src: '/assets/fields/wood.png', category: 'fields' ,isSelected:false},
    { title: 'Gras', src: '/assets/fields/gras.jpg', category: 'fields',isSelected:false },
    { title: 'Sand', src: '/assets/fields/sand.jpg', category: 'fields',isSelected:false },

    { title: 'Object 2', src: '/assets/objects/bonfire.png', category: 'objects' ,isSelected:false},
  ];

  filteredImages:X[] = [...this.images];

  constructor(private ref: DynamicDialogRef) {
    this.filterImages();
  }

  filterImages() {
    this.filteredImages = this.images.filter((image) => {
      const matchesCategory =
        !this.selectedCategory || image.category === this.selectedCategory;
      const matchesSearch =
        !this.searchText ||
        image.title.toLowerCase().includes(this.searchText.toLowerCase());
      return matchesCategory && matchesSearch;
    });
  }

  selectImage(image: X) {
    this.selectedImage = image;
    this.images.forEach(image => image.isSelected=false);
    image.isSelected=true;
    console.log('Selected image:', image);
  }

  close() {
    this.ref.close();
  }
}
