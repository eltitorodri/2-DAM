import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { TrendTopicComponent } from './trend-topic.component';

describe('TrendTopicComponent', () => {
  let component: TrendTopicComponent;
  let fixture: ComponentFixture<TrendTopicComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [TrendTopicComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(TrendTopicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
