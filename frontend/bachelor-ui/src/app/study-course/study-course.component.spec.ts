import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyCourseComponent } from './study-course.component';

describe('StudyCourseComponent', () => {
  let component: StudyCourseComponent;
  let fixture: ComponentFixture<StudyCourseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudyCourseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudyCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
