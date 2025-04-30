import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IStudyCourse} from '../model/IStudyCourse';
import {ISemester} from '../model/ISemester';

@Injectable({
  providedIn: 'root'
})
export class StudyCourseService {
  private readonly baseURLConfig= 'http://localhost:8080/study-course';

  constructor(private readonly http: HttpClient) { }

  getAllStudyCourses() {
    return this.http.get<IStudyCourse[]>(`${this.baseURLConfig}`);
  }

  getSemestersByStudyCourse(id: string) {
    return this.http.get<ISemester[]>(`${this.baseURLConfig}s/${id}/semesters`);
  }

  createStudyCourse(studyCourse: IStudyCourse) {
    console.log('Sending payload to server Service:', studyCourse);
    return this.http.post<IStudyCourse>(`${this.baseURLConfig}`, studyCourse);
  }

  updateStudyCourse(id: string, studyCourse: IStudyCourse) {
    console.log('Sending payload to server:', studyCourse);
    return this.http.put(`${this.baseURLConfig}/${id}`, studyCourse);
  }


  deleteStudyCourse(id: string) {
    return this.http.delete(`${this.baseURLConfig}/${id}`);
  }
}
