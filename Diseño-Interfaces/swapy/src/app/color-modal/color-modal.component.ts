import { Component, EventEmitter, Output, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-color-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="modal-overlay" (click)="cerrarModal()">
      <div class="modal-content" (click)="$event.stopPropagation()">
        <div class="modal-header">
          <h3>Añadir nuevo color</h3>
          <button class="close-btn" (click)="cerrarModal()">×</button>
        </div>

        <div class="modal-body">
          <label class="modal-label">Nombre del color</label>
          <input
            type="text"
            class="modal-input"
            placeholder="Ej: Azul marino"
            [(ngModel)]="nombreColor"
            (keyup.enter)="agregarColor()">
        </div>

        <div class="modal-footer">
          <button
            class="btn-cancelar"
            (click)="cerrarModal()">
            Cancelar
          </button>
          <button
            class="btn-agregar"
            (click)="agregarColor()"
            [disabled]="!nombreColor().trim()">
            Añadir
          </button>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .modal-overlay {
      position: fixed;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background: rgba(0, 0, 0, 0.5);
      display: flex;
      align-items: center;
      justify-content: center;
      z-index: 1000;
      animation: fadeIn 0.2s ease;
    }

    @keyframes fadeIn {
      from { opacity: 0; }
      to { opacity: 1; }
    }

    .modal-content {
      background: white;
      border-radius: 16px;
      padding: 24px;
      min-width: 320px;
      max-width: 90%;
      box-shadow: 0 12px 40px rgba(0, 0, 0, 0.2);
      animation: slideUp 0.3s ease;
    }

    @keyframes slideUp {
      from {
        transform: translateY(20px);
        opacity: 0;
      }
      to {
        transform: translateY(0);
        opacity: 1;
      }
    }

    .modal-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20px;
    }

    .modal-header h3 {
      margin: 0;
      font-size: 18px;
      font-weight: 600;
      color: #333;
    }

    .close-btn {
      background: none;
      border: none;
      font-size: 28px;
      color: #999;
      cursor: pointer;
      line-height: 1;
      padding: 0;
      width: 30px;
      height: 30px;
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: 4px;
      transition: all 0.2s ease;
    }

    .close-btn:hover {
      background: #f0f0f0;
      color: #666;
    }

    .modal-body {
      margin-bottom: 24px;
    }

    .modal-label {
      display: block;
      font-size: 14px;
      font-weight: 500;
      color: #555;
      margin-bottom: 8px;
    }

    .modal-input {
      background-color: #e4e4e4ff;
      width: 100%;
      padding: 12px 16px;
      border: 1px solid #5c5c5cff;
      border-radius: 10px;
      font-size: 14px;
      color: #000000ff;
      box-sizing: border-box;
      transition: border-color 0.2s ease;
    }

    .modal-input:focus {
      outline: none;
      border-color: #8b7bb8;
    }

    .modal-input::placeholder {
      color: #999;
    }

    .modal-footer {
      display: flex;
      gap: 12px;
      justify-content: flex-end;
    }

    .btn-cancelar,
    .btn-agregar {
      padding: 10px 24px;
      border: none;
      border-radius: 10px;
      font-size: 14px;
      font-weight: 600;
      cursor: pointer;
      transition: all 0.2s ease;
    }

    .btn-cancelar {
      background: #f0f0f0;
      color: #666;
    }

    .btn-cancelar:hover {
      background: #e0e0e0;
    }

    .btn-agregar {
      background: linear-gradient(135deg, #7b3ff2 0%, #9b5ff2 100%);
      color: white;
    }

    .btn-agregar:hover:not(:disabled) {
      transform: translateY(-1px);
      box-shadow: 0 4px 12px rgba(123, 63, 242, 0.3);
    }

    .btn-agregar:disabled {
      opacity: 0.5;
      cursor: not-allowed;
    }
  `]
})
export class ColorModalComponent {
  @Output() cerrar = new EventEmitter<void>();
  @Output() agregar = new EventEmitter<string>();

  nombreColor = signal('');

  cerrarModal() {
    this.cerrar.emit();
  }

  agregarColor() {
    const nombre = this.nombreColor().trim();
    if (nombre) {
      this.agregar.emit(nombre);
      this.nombreColor.set('');
    }
  }
}
