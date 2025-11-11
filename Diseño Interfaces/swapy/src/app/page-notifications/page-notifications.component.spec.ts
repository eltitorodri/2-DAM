import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { PageNotificationsComponent } from './page-notifications.component';

describe('PageNotificationsComponent', () => {
  let component: PageNotificationsComponent;
  let fixture: ComponentFixture<PageNotificationsComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [PageNotificationsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PageNotificationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
