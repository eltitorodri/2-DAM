import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { PreviousViewComponent } from './previous-view.component';

describe('PreviousViewComponent', () => {
  let component: PreviousViewComponent;
  let fixture: ComponentFixture<PreviousViewComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [PreviousViewComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PreviousViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
