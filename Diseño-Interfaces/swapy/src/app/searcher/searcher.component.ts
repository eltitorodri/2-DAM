import { Component, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-searcher',
  templateUrl: './searcher.component.html',
  styleUrls: ['./searcher.component.scss'],
  standalone: true,
})
export class SearcherComponent {

  @Output() search = new EventEmitter<string>();
  @ViewChild('searchInput') searchInput!: ElementRef<HTMLInputElement>;

  isOpen = false;
  query = '';

  openSearch(): void {
    this.isOpen = true;
    document.body.style.overflow = 'hidden';
    setTimeout(() => {
      this.searchInput.nativeElement.focus();
    }, 100);
  }

  closeSearch(): void {
    this.isOpen = false;
    this.query = '';
    document.body.style.overflow = '';
  }

  performSearch(): void {
    if (this.query.trim()) {
      this.search.emit(this.query);
      this.closeSearch();
    }
  }

  handleKeyPress(event: KeyboardEvent): void {
    if (event.key === 'Enter') {
      this.performSearch();
    } else if (event.key === 'Escape') {
      this.closeSearch();
    }
  }

  onInputChange(value: string): void {
    this.query = value;
  }

}

