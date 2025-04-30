import { TestBed } from '@angular/core/testing';

import { LecturerTypeService } from './lecturer-type.service';

describe('LecturerTypeService', () => {
  let service: LecturerTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LecturerTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
