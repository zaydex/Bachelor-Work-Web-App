import {Component, inject, OnInit} from '@angular/core';
import {TableComponent} from '../shared/components/table/table.component';
import {CourseSemesterService} from './services/course-semester.service';
import {MatTableDataSource} from '@angular/material/table';
import {ICourseSemester} from './model/ICourseSemester';
import {IStudyCourse} from '../study-course/model/IStudyCourse';
import {ISemester} from '../study-course/model/ISemester';
import {StudyCourseService} from '../study-course/services/study-course.service';
import {CourseService} from '../course/services/course.service';
import {ICourse} from '../course/model/icourse';
import {FormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-course-semester',
  standalone: true,
  imports: [TableComponent, MatFormFieldModule, MatSelectModule, FormsModule, NgForOf],
  templateUrl: './course-semester.component.html',
  styleUrl: './course-semester.component.css'
})
export class CourseSemesterComponent implements OnInit{
  protected courseSemesterService: CourseSemesterService = inject(CourseSemesterService);
  protected studyCourseService: StudyCourseService = inject(StudyCourseService);
  protected courseService: CourseService= inject(CourseService);


  private currentStudyCourseId: string | undefined;

  selectedStudyCourse: IStudyCourse = {} as IStudyCourse;
  selectedSemester: ISemester = {} as ISemester;

  studyCourses: IStudyCourse[] = [];
  semesters: ISemester[] = [];
  courseSemesters: ICourseSemester[] = [];


  title: string = 'Course Semester';
  dataSource = new MatTableDataSource<ICourseSemester>();

  myColumns = [
    {columnDef: 'courseName', header: 'Course', cell: (row: any) => row.course?.courseName ?? ''},
    {columnDef: 'semesterName', header: 'Semester', cell: (row: any) => row.semester?.semesterNumber ?? ''},
    {columnDef: 'toPlan', header: 'To Plan', cell: (row: any) => row.toPlan},
  ];
  displayedColumns = [...this.myColumns.map(col => col.columnDef), 'action'];

  formConfig = {
    fields: [
      {name: 'studyCourse', type: 'select', label: 'Study Course', required: true, options: [], compareWith: (o1: any, o2: any) => o1.id === o2.id},
      {name: 'semester', type: 'select', label: 'Semester', required: true, options: [], compareWith: (o1: any, o2: any) => o1.id === o2.id},
      {name: 'course', type: 'select', label: 'Course', required: true, options: [], compareWith: (o1: any, o2: any) => o1.id === o2.id},
      {name: 'toPlan', type: 'checkbox', label: 'To Plan', required: true},
    ],
    controls: {
      studyCourse: null,
      semester: null,
      course: null,
      toPlan: false,
    }
  }


  ngOnInit() {
    this.loadData();
    this.loadCourses();
  }


  loadData(){
    this.currentStudyCourseId = this.selectedStudyCourse.id;
    this.courseSemesterService.getAllCourseSemesters().subscribe((data: ICourseSemester[]) => {
      this.courseSemesters = data.sort((a,b) => a.course.courseName.localeCompare(b.course.courseName));
      this.loadStudyCourses();
    })
  }

  loadCourses(){
    this.courseService.getAllCourses().subscribe((data: ICourse[]) => {
      const courseField = this.formConfig.fields.find(f  => f.name === 'course') as any;

      if(courseField) {
        courseField.options = data.map(c => ({
          value: c,
          label: c.courseName
        }));
      }
    })
  }

  loadStudyCourses(){
    this.studyCourseService.getAllStudyCourses().subscribe((data: IStudyCourse[]) => {
      const studyCourseField = this.formConfig.fields.find(f => f.name === 'studyCourse') as any;

      this.studyCourses = data;
      if (this.studyCourses.length > 0) {
        if (this.currentStudyCourseId) {
          this.selectedStudyCourse = this.studyCourses.find(sc => sc.id === this.currentStudyCourseId) || this.studyCourses[0];
        } else {
          this.selectedStudyCourse = this.studyCourses[0];
        }
        this.loadSemestersForStudyCourse();
      }

      if(studyCourseField){
        studyCourseField.options = data.map(sc => ({
          value: sc,
          label: sc.studyCourseName
        }));
      }
    })
  }

  loadSemestersForStudyCourse(){
    this.studyCourseService.getSemestersByStudyCourse(this.selectedStudyCourse.id!).subscribe((semesters: ISemester[]) => {
      this.semesters = [{ id: 'all', semesterNumber: 0}, ...semesters];
      this.selectedSemester = this.semesters[0];
      this.filterCoursesBySemester();
    })
  }

  filterCoursesBySemester() {
    const tableData = this.courseSemesters.map(cs => ({
      ...cs,
      course: cs.course,
      semester: cs.semester,
      studyCourse: cs.semester.studyCourse,
      toPlan: cs.toPlan,
    }) as any)

    if(this.selectedSemester.id === 'all'){
      this.dataSource.data = tableData.filter(cs =>
        cs.semester?.studyCourse?.id == this.selectedStudyCourse.id);
    }
    else{
      this.dataSource.data = tableData.filter(cs =>
        cs.semester.id === this.selectedSemester.id);
    }
  }

  onSave(data: any) {
    if(data.id){
      this.courseSemesterService.updateCourseSemester(data.id, data).subscribe(() => {
        this.loadData();
      });
    }
    else{
      this.courseSemesterService.createCourseSemester(data).subscribe(() => {
        this.loadData();
      })
    }
  }

  onDelete(data: any){
    if(data.id) {
      this.courseSemesterService.deleteCourseSemester(data.id).subscribe(() => {
        this.loadData();
      })
    }
  }

  onSemestersReceived(semesters: ISemester[]) {
    this.semesters = [{ id: 'all', semesterNumber: 0}, ...semesters];
    this.selectedSemester = this.semesters[0];
    this.filterCoursesBySemester();

    const semesterField = this.formConfig.fields.find(f => f.name === 'semester') as any;

    if(semesterField){
      semesterField.options = semesters.map(s => ({
        value: s,
        label: s.semesterNumber,
      }))
    }
  }
}
