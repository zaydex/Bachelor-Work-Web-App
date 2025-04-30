import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LecturerTypeComponent } from './lecturer-type.component';

describe('LecturerTypeComponent', () => {
  let component: LecturerTypeComponent;
  let fixture: ComponentFixture<LecturerTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LecturerTypeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LecturerTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
