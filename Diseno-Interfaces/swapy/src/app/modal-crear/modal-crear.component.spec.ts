import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ModalCrearComponent } from './modal-crear.component';

describe('ModalCrearComponent', () => {
  let component: ModalCrearComponent;
  let fixture: ComponentFixture<ModalCrearComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [ModalCrearComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ModalCrearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
