import { TestBed } from '@angular/core/testing';

import { CourseSemesterLecturerService } from './course-semester-lecturer.service';

describe('CourseSemesterLecturerService', () => {
  let service: CourseSemesterLecturerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CourseSemesterLecturerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
