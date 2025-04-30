import { TestBed } from '@angular/core/testing';

import { StudyCourseService } from './study-course.service';

describe('StudyCourseService', () => {
  let service: StudyCourseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudyCourseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
