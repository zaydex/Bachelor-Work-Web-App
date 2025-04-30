import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseSemesterComponent } from './course-semester.component';

describe('CourseSemesterComponent', () => {
  let component: CourseSemesterComponent;
  let fixture: ComponentFixture<CourseSemesterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseSemesterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseSemesterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
