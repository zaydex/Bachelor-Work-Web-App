import {AfterViewInit, Component, inject} from '@angular/core';
import {TableComponent} from '../shared/components/table/table.component';
import {CourseSemesterLecturerService} from './services/course-semester-lecturer.service';
import {MatTableDataSource} from '@angular/material/table';
import {ICourseSemesterLecturer} from './model/ICourseSemesterLecturer';
import {CourseSemesterService} from '../course-semester/services/course-semester.service';
import {LecturerService} from '../lecturer/sevices/lecturer.service';
import {ICourseSemester} from '../course-semester/model/ICourseSemester';
import {ILecturer} from '../lecturer/model/ILecturer';

@Component({
  selector: 'app-course-semester-lecturer',
  standalone: true,
  imports: [TableComponent],
  templateUrl: './course-semester-lecturer.component.html',
  styleUrl: './course-semester-lecturer.component.css'
})
export class CourseSemesterLecturerComponent implements AfterViewInit {
  protected courseSemesterLecturerService: CourseSemesterLecturerService = inject(CourseSemesterLecturerService);
  protected courseSemesterService: CourseSemesterService = inject(CourseSemesterService);
  protected lecturerService: LecturerService = inject(LecturerService);

  title = 'Course Semester Lecturer';
  dataSource = new MatTableDataSource<ICourseSemesterLecturer>();

  myColumns = [
    {columnDef: 'courseName', header: 'Course', cell: (row: any) => row.courseSemester?.course?.courseName ?? ''},
    {columnDef: 'studyCourseName', header: 'Study Course', cell: (row: any) => row.courseSemester?.semester?.studyCourse?.studyCourseName ?? ''},
    {columnDef: 'semesterNumber', header: 'Semester', cell: (row: any) => row.courseSemester?.semester?.semesterNumber ?? ''},
    {columnDef: 'lecturerName', header: 'Lecturer', cell: (row: any) => row.lecturer?.name ?? ''},
    {columnDef: 'comment', header: 'Comment', cell: (row: any) => row.comment ?? ''},
    {columnDef: 'numberOfStudents', header: 'Number of students', cell: (row: any) => row.numberOfStudents ?? ''},
    {columnDef: 'numberOfGroups', header: 'Number of groups', cell: (row: any) => row.numberOfGroups ?? ''},
  ];
  displayedColumns = [...this.myColumns.map(col => col.columnDef), 'action'];

  formConfig = {
    fields: [
      {name: 'courseSemester', type: 'select', label: 'Course Semester', required: true, options: [], compareWith: (o1: any, o2: any) => o1.id === o2.id},
      {name: 'courseSemester2', type: 'select', label: 'Additional Course Semester', required: false, options: [], compareWith: (o1: any, o2: any) => o1.id === o2.id},
      {name: 'lecturer', type: 'select', label: 'Lecturer', required: true, options: [], compareWith: (o1: any, o2: any) => o1.id === o2.id},
      {name: 'comment', type: 'text', label: 'Comment', required: false},
      {name: 'numberOfStudents', type: 'number', label: 'Number of students', required: false},
      {name: 'numberOfGroups', type: 'number', label: 'Number of groups', required: false},
    ],
    controls: {
      courseSemester: null,
      courseSemester2: null,
      lecturer: null,
      comment: null,
      numberOfStudents: null,
      numberOfGroups: null,
    }
  }

  ngAfterViewInit() {
    this.loadData();
    this.loadCourseSemesters();
    this.loadLecturers();
  }

  loadData() {
    this.courseSemesterLecturerService.getAllCourseSemesterLecturers().subscribe((data: ICourseSemesterLecturer[]) => {
      this.dataSource.data = data;
    });
  }

  loadCourseSemesters() {
    this.courseSemesterService.getAllCourseSemesters().subscribe((data: ICourseSemester[]) => {
      const filteredData = data.filter(cs => cs.toPlan);

      const options = filteredData.map(cs => ({
        value: cs,
        label: cs.course.courseName,
        studyCourseName: cs.semester.studyCourse?.studyCourseName,
        semesterNumber: cs.semester.semesterNumber,
      }));

      ['courseSemester', 'courseSemester2'].forEach(fieldName => {
        const field = this.formConfig.fields.find(f => f.name === fieldName) as any;
        if (field) {
          field.options = options;
          field.additionalFields = {
            studyCourseName: true,
            semesterNumber: true,
          };
        }
      });
    })
  }

  loadLecturers() {
    this.lecturerService.getAllLecturers().subscribe((data: ILecturer[]) => {
      const lecturerField = this.formConfig.fields.find(f => f.name === 'lecturer') as any;

      if (lecturerField) {
        lecturerField.options = data.map(l => ({
          value: l,
          label: l.name
        }))
      }
    })
  }

  onSave(data: any) {
    const selectedCourseSemesters = [data.courseSemester, data.courseSemester2].filter(Boolean);

    if (selectedCourseSemesters.length === 0) {
      alert("Please select at least one CourseSemester");
      return;
    }

    selectedCourseSemesters.forEach(courseSemester => {
      const requestData = {
        courseSemester: courseSemester,
        lecturer: data.lecturer,
        comment: data.comment,
        numberOfStudents: data.numberOfStudents,
        numberOfGroups: data.numberOfGroups,
      };

      if (data.id) {
        this.courseSemesterLecturerService.updateCourseSemesterLecturer(data.id, requestData).subscribe(() => {
          this.loadData();
        })
      } else {
        this.courseSemesterLecturerService.createCourseSemesterLecturer(requestData).subscribe(() => {
          this.loadData();
        })
      }
    })
  }

  onDelete(data: any) {
    if (data.id) {
      this.courseSemesterLecturerService.deleteCourseSemesterLecturer(data.id).subscribe(() => {
        this.loadData();
      })
    }
  }
}
