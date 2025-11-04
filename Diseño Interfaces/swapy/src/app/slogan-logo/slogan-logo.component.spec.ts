import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { SloganLogoComponent } from './slogan-logo.component';

describe('SloganLogoComponent', () => {
  let component: SloganLogoComponent;
  let fixture: ComponentFixture<SloganLogoComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [SloganLogoComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SloganLogoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
