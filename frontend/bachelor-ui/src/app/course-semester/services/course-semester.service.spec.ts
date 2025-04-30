import { TestBed } from '@angular/core/testing';

import { CourseSemesterService } from './course-semester.service';

describe('CourseSemesterServiceService', () => {
  let service: CourseSemesterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CourseSemesterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
