import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { LogoSloganComponent } from './logo-slogan.component';

describe('LogoSloganComponent', () => {
  let component: LogoSloganComponent;
  let fixture: ComponentFixture<LogoSloganComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [LogoSloganComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(LogoSloganComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
