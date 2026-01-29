import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { UploadCloth1Component } from './upload-cloth1.component';

describe('UploadCloth1Component', () => {
  let component: UploadCloth1Component;
  let fixture: ComponentFixture<UploadCloth1Component>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [UploadCloth1Component],
    }).compileComponents();

    fixture = TestBed.createComponent(UploadCloth1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
