import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ITimePeriod} from '../model/ITimePeriod';

@Injectable({
  providedIn: 'root'
})
export class TimePeriodService {
  private readonly baseURLConfig = 'http://localhost:8080/time-period';

  constructor(private readonly http: HttpClient) {}

  getAllTimePeriods(){
    return this.http.get<ITimePeriod[]>(`${this.baseURLConfig}`);
  }

  createTimePeriod(timePeriod: ITimePeriod){
    return this.http.post<ITimePeriod>(`${this.baseURLConfig}`, timePeriod);
  }

  updateTimePeriod(id: string, timePeriod: ITimePeriod){
    return this.http.put(`${this.baseURLConfig}/${id}`, timePeriod);
  }

  deleteTimePeriod(id: string){
    return this.http.delete(`${this.baseURLConfig}/${id}`);
  }
}
