import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { MostLikedComponent } from './most-liked.component';

describe('MostLikedComponent', () => {
  let component: MostLikedComponent;
  let fixture: ComponentFixture<MostLikedComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [MostLikedComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(MostLikedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
