import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { BrandSelectorComponent } from './brand-selector.component';

describe('BrandSelectorComponent', () => {
  let component: BrandSelectorComponent;
  let fixture: ComponentFixture<BrandSelectorComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [BrandSelectorComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(BrandSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
