import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ClothCardComponent } from './cloth-card.component';

describe('ClothCardComponent', () => {
  let component: ClothCardComponent;
  let fixture: ComponentFixture<ClothCardComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [ClothCardComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ClothCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
});
