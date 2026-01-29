import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { CarrouselHorizontalComponent } from './carrousel-horizontal.component';

describe('CarrouselHorizontalComponent', () => {
  let component: CarrouselHorizontalComponent;
  let fixture: ComponentFixture<CarrouselHorizontalComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [CarrouselHorizontalComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CarrouselHorizontalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
