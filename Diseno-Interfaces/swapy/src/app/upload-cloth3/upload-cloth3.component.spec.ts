import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { UploadCloth3Component } from './upload-cloth3.component';

describe('UploadCloth3Component', () => {
  let component: UploadCloth3Component;
  let fixture: ComponentFixture<UploadCloth3Component>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [UploadCloth3Component],
    }).compileComponents();

    fixture = TestBed.createComponent(UploadCloth3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
