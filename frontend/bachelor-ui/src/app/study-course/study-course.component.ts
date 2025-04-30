import {AfterViewInit, Component, inject, ViewChild} from '@angular/core';
import {TableComponent} from '../shared/components/table/table.component';
import {MatTableDataSource} from '@angular/material/table';
import {IStudyCourse} from './model/IStudyCourse';
import {StudyCourseService} from './services/study-course.service';
import {MatSort} from '@angular/material/sort';
import {ITimePeriod} from '../time-period/model/ITimePeriod';
import {TimePeriodService} from '../time-period/services/time-period.service';

@Component({
  selector: 'app-study-course',
  standalone: true,
  imports: [TableComponent],
  templateUrl: './study-course.component.html',
  styleUrl: './study-course.component.css'
})
export class StudyCourseComponent implements AfterViewInit {
  //Services
  protected studyCourseService: StudyCourseService = inject(StudyCourseService);
  protected timePeriodService: TimePeriodService = inject(TimePeriodService);


  //My Variables
  title: string = 'Study Course';
  dataSource: MatTableDataSource<IStudyCourse> = new MatTableDataSource<IStudyCourse>();

  myColumns = [
    {columnDef: 'studyCourseName', header: 'Study Course', cell: (row: IStudyCourse) => row.studyCourseName ?? ''},
    {columnDef: 'numberOfSemesters', header: 'Semesters', cell: (row: IStudyCourse) => row.numberOfSemesters ?? 0},
    {columnDef: 'timePeriod', header: 'Time Period', cell: (row: IStudyCourse) => row.timePeriod?.academicYear ?? ''}
  ]
  displayedColumns: string[] = [...this.myColumns.map(col => col.columnDef), 'action'];

  formConfig = {
    fields: [
      { name: 'studyCourseName', type: 'text', label: 'Study Course', required: true},
      { name: 'numberOfSemesters', type: 'number', label: 'Semesters', required: true},
      { name: 'timePeriod', type: 'select', label: 'Time Period', options: [], required: true, compareWith: (o1: any, o2: any) => o1.id === o2.id}
    ],
    controls: {
      studyCourseName: null,
      numberOfSemesters: null,
      timePeriod: null,
    }
  }

  //Methods
  ngAfterViewInit() {
    this.loadData();
    this.loadTimePeriods();
  }

  loadData() {
    this.studyCourseService.getAllStudyCourses().subscribe((data: IStudyCourse[]) => {
      this.dataSource.data = data.sort((a,b) => a.studyCourseName.localeCompare(b.studyCourseName));
    });
  }

  loadTimePeriods() {
    this.timePeriodService.getAllTimePeriods().subscribe((data: ITimePeriod[]) => {
      const timePeriodField = this.formConfig.fields.find(f => f.name === 'timePeriod') as any;

      if (timePeriodField) {
        timePeriodField.options = data.map(tp => ({
          value: tp,
          label: tp.academicYear
        }));
      }
    });
  }

  onSave(data: any) {
    // Если есть ID, обновляем существующий объект, иначе создаем новый
    if (data.id) {
      this.studyCourseService.updateStudyCourse(data.id, data).subscribe({
        next: (updated) => {
          console.log('Successfully updated:', updated);
          this.loadData();
        },
        error: (err) => {
          console.error('Error while updating:', err);
        }
      })
    }
    else {
      this.studyCourseService.createStudyCourse(data).subscribe({
        next: (created) => {
          console.log('Successfully created:', created);
          this.loadData();
        },
        error: (err) => {
          console.error('Error while creating:', err);
        }
      })
    }
  }


  onDelete(row: IStudyCourse) {
    if (row.id) {
      this.studyCourseService.deleteStudyCourse(row.id).subscribe({
        next: () => {
          console.log('Successfully deleted:', row.id);
          this.loadData(); // Обновляем таблицу после удаления
        },
        error: (err) => {
          console.error('Error while deleting:', err);
        },
      });
    } else {
      console.error('ID not found for the row:', row);
    }
  }
}
