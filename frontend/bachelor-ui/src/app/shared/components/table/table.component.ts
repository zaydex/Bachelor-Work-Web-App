import {AfterViewInit, Component, EventEmitter, inject, Input, Output, ViewChild} from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {NgForOf, NgIf} from '@angular/common';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {FormComponent} from '../form/form.component';
import {ISemester} from '../../../study-course/model/ISemester';
import {StudyCourseService} from '../../../study-course/services/study-course.service';
import {FormsModule} from '@angular/forms';
import {MatCheckboxModule} from '@angular/material/checkbox';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [MatTableModule, MatInputModule, MatIconModule, MatButtonModule, NgForOf, MatSortModule, MatDialogModule, MatSortModule, FormsModule, MatCheckboxModule, NgIf],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent implements AfterViewInit{
  protected studyCourseService = inject(StudyCourseService);

  @Input() title: string = '';
  @Input() dataSource: MatTableDataSource<any> = new MatTableDataSource<any>();
  @Input() columns: { columnDef: string; header: string; cell: (row: any) => any }[] = [];
  @Input() displayedColumns: string[] = [];
  @Input() formConfig: any;

  @Output() save = new EventEmitter<any>(); // Добавляем событие сохранения
  @Output() delete = new EventEmitter<any>(); // Событие удаления
  @Output() sendSemesters = new EventEmitter<ISemester[]>();

  @ViewChild(MatSort) matSort!: MatSort;

  constructor(private readonly dialog: MatDialog) {}

  ngAfterViewInit() {
    this.dataSource.sort = this.matSort; // Привязываем MatSort после инициализации компонента

    this.dataSource.filterPredicate = (data, filter) => {
      const normalizedFilter = filter.trim().toString().toLowerCase();

      const specificFilters = (
        data.course?.courseName?.toLowerCase().includes(normalizedFilter) ||
        data.semester?.semesterNumber?.toString().includes(normalizedFilter) ||
        data.lecturer?.name?.toLowerCase().includes(normalizedFilter) ||

        data.lecturerType?.typeName?.toLowerCase().includes(normalizedFilter) ||
        data.lecturerType?.requiredHours?.toString().includes(normalizedFilter) ||

        data.timePeriod?.academicYear?.toLowerCase().includes(normalizedFilter) ||

        data.courseSemester?.course?.courseName?.toLowerCase().includes(normalizedFilter) ||
        data.courseSemester?.semester?.studyCourse?.studyCourseName?.toLowerCase().includes(normalizedFilter)
      );

      const generalFilters = Object.keys(data).some(key =>
        data[key]?.toString().toLowerCase().includes(normalizedFilter)
      );

      return specificFilters || generalFilters;
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(FormComponent, {
      width: '400px',
      height: 'auto',
      data: {
        title: `Add ${this.title}`,
        fields: this.formConfig.fields,
        controls: this.formConfig.controls
      }
    });

    dialogRef.afterOpened().subscribe(() => {
      dialogRef.componentInstance.sendSemesters.subscribe(semesters => {
        this.sendSemesters.emit(semesters);
      });
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.save.emit(result);
      }
    });
  }

  //Filter for 'Search' Input
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  onEdit(row: any) {
    if ('toPlan' in row) {
      this.studyCourseService.getSemestersByStudyCourse(row.semester.studyCourse.id).subscribe(semesters => {
        this.sendSemesters.emit(semesters);
      })
    }
    const dialogRef = this.dialog.open(FormComponent, {
      width: '400px',
      height: 'auto',
      data: {
        title: `Edit ${this.title}`,
        fields: this.formConfig.fields,
        controls: {
          ...row
        }
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.save.emit({ ...row, ...result}); // Обновляем данные, передавая старые + новые
      }
    })
  }

  onDelete(row: any) {
    this.delete.emit(row); // Генерируем событие с текущей строкой
  }

  onCheckboxChange(row: any) {
    this.save.emit(row); // Передает изменения в родительский компонент
  }
}
