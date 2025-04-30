import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import ILecturerType from '../model/ILecturer-type';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LecturerTypeService {
  private readonly baseURLConfig = 'http://localhost:8080/lecturer-type';

  constructor(private readonly http: HttpClient) {}

  getAllLecturerTypes(): Observable<ILecturerType[]> {
    return this.http.get<ILecturerType[]>(`${this.baseURLConfig}`)
  }
  createLecturerType(lecturerType: ILecturerType): Observable<ILecturerType> {
    return this.http.post<ILecturerType>(`${this.baseURLConfig}`, lecturerType)
  }
  updateLecturerType(id: string, lecturerType: ILecturerType): Observable<ILecturerType> {
    return this.http.put<ILecturerType>(`${this.baseURLConfig}/${(id)}`, lecturerType)
  }
  deleteLecturerType(id: string): Observable<any> {
    return this.http.delete<any>(`${this.baseURLConfig}/${id}`)
  }
}
