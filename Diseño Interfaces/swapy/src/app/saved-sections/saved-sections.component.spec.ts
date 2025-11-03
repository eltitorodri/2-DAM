import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { SavedSectionsComponent } from './saved-sections.component';

describe('SavedSectionsComponent', () => {
  let component: SavedSectionsComponent;
  let fixture: ComponentFixture<SavedSectionsComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [SavedSectionsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SavedSectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
