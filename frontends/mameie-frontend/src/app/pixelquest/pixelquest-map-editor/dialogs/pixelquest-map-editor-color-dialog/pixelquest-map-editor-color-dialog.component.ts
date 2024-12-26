import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { DynamicDialogRef } from 'primeng/dynamicdialog';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-pixelquest-map-editor-color-dialog',
  standalone: true,
  imports: [CommonModule, InputTextModule, ButtonModule, DropdownModule, FormsModule],
  templateUrl: './pixelquest-map-editor-color-dialog.component.html',
  styleUrl: './pixelquest-map-editor-color-dialog.component.scss'
})
export class PixelquestMapEditorColorDialogComponent {
  searchText = '';
  selectedCategory: string | null = null;
  categories = [
    { label: 'Fields', value: 'fields' },
    { label: 'Objects', value: 'objects' },
  ];

  images = [
    { title: 'Field 1', src: '/assets/stone_ground_field.png', category: 'fields' },
    { title: 'Field 2', src: '/assets/fields/wood.png', category: 'fields' },
    { title: 'Object 1', src: '/assets/stone_ground_field.png', category: 'objects' },
    { title: 'Object 2', src: '/assets/stone_ground_field.png', category: 'objects' },
  ];

  filteredImages = [...this.images];

  constructor(private ref: DynamicDialogRef) { }

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

  selectImage(image: any) {
    console.log('Selected image:', image);
  }

  close() {
    this.ref.close();
  }
}
