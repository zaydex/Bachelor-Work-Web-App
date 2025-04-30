import {AfterViewInit, Component, inject} from '@angular/core';
import {TableComponent} from '../shared/components/table/table.component';
import {CourseService} from './services/course.service';
import {MatTableDataSource} from '@angular/material/table';
import {ICourse} from './model/icourse';

@Component({
  selector: 'app-course',
  standalone: true,
  imports: [TableComponent],
  templateUrl: './course.component.html',
  styleUrl: './course.component.css'
})
export class CourseComponent implements AfterViewInit{
  protected courseService: CourseService = inject(CourseService);

  title: string = 'Course';
  dataSource: MatTableDataSource<ICourse> = new MatTableDataSource<ICourse>();
  myColumns = [
    {columnDef: 'courseName', header: 'Course', cell: (row: ICourse) => row.courseName},
    {columnDef: 'weeklyLectureHours', header: 'Weekly Lecture Hours', cell: (row: ICourse) => row.weeklyLectureHours},
    {columnDef: 'weeklyExerciseHours', header: 'Weekly Exercise Hours', cell: (row: ICourse) => row.weeklyExerciseHours},
    {columnDef: 'creditPoints', header: 'Credit points', cell: (row: ICourse) => row.creditPoints},
    {columnDef: 'isMandatory', header: 'Mandatory', cell: (row: ICourse) => row.isMandatory ? 'Yes' : 'No'},
  ]
  displayedColumns = [...this.myColumns.map(col => col.columnDef), 'action'];
  formConfig = {
    fields: [
      { name: 'courseName', type: 'text', label: 'Course', required: true},
      { name: 'weeklyLectureHours', type: 'number', label: 'Weekly Lecture Hours', required: true},
      { name: 'weeklyExerciseHours', type: 'number', label: 'Weekly Exercise Hours', required: true},
      { name: 'creditPoints', type: 'number', label: 'Credit points', required: true},
      { name: 'isMandatory', type: 'checkbox', label: 'Mandatory', required: true},
    ],
    controls: {
      courseName: null,
      weeklyLectureHours: null,
      weeklyExerciseHours: null,
      isMandatory: false,
      creditPoints: null,
    }
  }

  ngAfterViewInit() {
    this.loadData();
  }

  loadData() {
    this.courseService.getAllCourses().subscribe((data: ICourse[]) => {
      this.dataSource.data = data.sort((a,b) => a.courseName.localeCompare(b.courseName));
    });
  }

  onSave(data: any) {
    if (data.id) {
      this.courseService.updateCourse(data.id, data).subscribe({
        next: (updated) => {
          console.log('Successfully updated:', updated);
          this.loadData();
        },
        error: (err) => {
          console.error('Error while updating:', err);
        }
      });
    }
    else{
      this.courseService.createCourse(data).subscribe({
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

  onDelete(row: ICourse) {
    if (row.id) {
      this.courseService.deleteCourse(row.id).subscribe({
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
