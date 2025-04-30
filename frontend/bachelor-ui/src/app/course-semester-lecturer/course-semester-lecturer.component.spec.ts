import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseSemesterLecturerComponent } from './course-semester-lecturer.component';

describe('CourseSemesterLecturerComponent', () => {
  let component: CourseSemesterLecturerComponent;
  let fixture: ComponentFixture<CourseSemesterLecturerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseSemesterLecturerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseSemesterLecturerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
