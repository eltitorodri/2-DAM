import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { UploadCloth2Component } from './upload-cloth2.component';

describe('UploadCloth2Component', () => {
  let component: UploadCloth2Component;
  let fixture: ComponentFixture<UploadCloth2Component>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [UploadCloth2Component],
    }).compileComponents();

    fixture = TestBed.createComponent(UploadCloth2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
