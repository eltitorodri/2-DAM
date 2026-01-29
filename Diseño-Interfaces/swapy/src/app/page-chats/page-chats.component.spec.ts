import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { PageChatsComponent } from './page-chats.component';

describe('PageChatsComponent', () => {
  let component: PageChatsComponent;
  let fixture: ComponentFixture<PageChatsComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [PageChatsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PageChatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
