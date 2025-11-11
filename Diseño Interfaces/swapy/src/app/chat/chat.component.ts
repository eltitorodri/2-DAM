import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

interface Meetup {
  location: string | null;
  date: Date | null;
  time: string | null;
}

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {
  showConfiguration = false;
  showLocationModal = false;
  showDateModal = false;
  showTimeModal = false;

  meetup: Meetup = {
    location: null,
    date: null,
    time: null
  };

  selectedDate = new Date();
  selectedHour = 7;
  selectedMinute = 0;
  selectedPeriod = 'AM';
  calendarDays: number[] = [];
  minuteValues: number[] = [];

  ngOnInit(): void {
    this.generateCalendarDays();
    this.generateMinuteValues();
  }
  
  get formattedSelectedHour(): string {
    return String(this.selectedHour).padStart(2, '0');
  }

  get formattedSelectedMinute(): string {
    return String(this.selectedMinute).padStart(2, '0');
  }

  toggleConfiguration(): void {
    this.showConfiguration = !this.showConfiguration;
  }

  openLocationPicker(): void {
    this.showConfiguration = false;
    this.showLocationModal = true;
  }

  openDatePicker(): void {
    this.showConfiguration = false;
    this.showDateModal = true;
  }

  openTimePicker(): void {
    this.showConfiguration = false;
    this.showTimeModal = true;
  }

  closeModal(): void {
    this.showLocationModal = false;
    this.showDateModal = false;
    this.showTimeModal = false;
  }

  confirmLocation(): void {
    this.meetup.location = 'El Cerro Del Aguila';
    this.closeModal();
    this.showConfiguration = true;
  }

  confirmDate(): void {
    this.meetup.date = this.selectedDate;
    this.closeModal();
    this.showConfiguration = true;
  }
  
  // FUNCIÓN AÑADIDA PARA SOLUCIONAR EL FALLO DEL (click)
  selectMinute(minute: number): void {
    this.selectedMinute = minute;
  }

  confirmTime(): void {
    const hour = this.selectedPeriod === 'PM' ? 
      (this.selectedHour === 12 ? 12 : this.selectedHour + 12) : 
      (this.selectedHour === 12 ? 0 : this.selectedHour);
    
    this.meetup.time = `${String(hour).padStart(2, '0')}:${String(this.selectedMinute).padStart(2, '0')}`;
    this.closeModal();
    this.showConfiguration = true;
  }

  selectDate(day: number): void {
    const year = this.selectedDate.getFullYear();
    const month = this.selectedDate.getMonth();
    this.selectedDate = new Date(year, month, day);
  }

  generateCalendarDays(): void {
    const year = this.selectedDate.getFullYear();
    const month = this.selectedDate.getMonth();
    
    const daysInMonth = new Date(year, month + 1, 0).getDate();

    this.calendarDays = [];
    for (let i = 1; i <= daysInMonth; i++) {
      this.calendarDays.push(i);
    }
  }
  
  generateMinuteValues(): void {
    this.minuteValues = [];
    for (let i = 0; i < 60; i += 5) {
      this.minuteValues.push(i);
    }
  }
}