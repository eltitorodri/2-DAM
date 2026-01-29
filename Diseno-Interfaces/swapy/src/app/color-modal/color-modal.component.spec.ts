import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ColorModalComponent } from './color-modal.component';

describe('ColorModalComponent', () => {
  let component: ColorModalComponent;
  let fixture: ComponentFixture<ColorModalComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [ColorModalComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ColorModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
