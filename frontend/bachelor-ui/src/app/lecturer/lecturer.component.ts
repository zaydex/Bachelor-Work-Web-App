import {TableComponent} from '../shared/components/table/table.component';
import {AfterViewInit, Component, inject} from '@angular/core';
import {LecturerService} from './sevices/lecturer.service';
import {ILecturer} from './model/ILecturer';
import {MatTableDataSource} from '@angular/material/table';
import {LecturerTypeService} from '../lecturer-type/services/lecturer-type.service';
import ILecturerType from '../lecturer-type/model/ILecturer-type';

@Component({
  selector: 'app-lecturer',
  standalone: true,
  imports: [TableComponent],
  templateUrl: './lecturer.component.html',
  styleUrl: './lecturer.component.css'
})
export class LecturerComponent implements AfterViewInit {
  //Service Class
  protected lecturerService: LecturerService = inject(LecturerService);
  protected lecturerTypeService: LecturerTypeService = inject(LecturerTypeService);

  title: string = 'Lecturer';
  dataSource = new MatTableDataSource<ILecturer>();

  myColumns = [
    { columnDef: 'name', header: 'Name', cell: (row: ILecturer) => row.name },
    { columnDef: 'lecturerTypeName', header: 'Lecturer Type', cell: (row: ILecturer) => row.lecturerType?.typeName ?? '' },
    { columnDef: 'lecturerTypeRequiredHours', header: 'Required Hours', cell: (row: ILecturer) => row.lecturerType?.requiredHours ?? '' },
    { columnDef: 'lecturerTypeLectureship', header: 'Lectureship', cell: (row: ILecturer) => row.lecturerType?.lectureship ? 'Yes' : 'No' }
  ];
  displayedColumns = [...this.myColumns.map(col => col.columnDef), 'action'];

  formConfig = {
    fields: [
      {name: 'name', type: 'text', label: 'Name', required: true},
      {name: 'lecturerType', type: 'select', label: 'Lecturer Type', required: true, options: [], compareWith: (o1: any, o2: any) => o1.id === o2.id}
    ],
    controls: {
      name: null,
      lecturerType: null,
    }
  }

  ngAfterViewInit() {
    this.loadData();
    this.loadLecturerTypes();
  }

  loadData() {
    this.lecturerService.getAllLecturers().subscribe((data: ILecturer[]) => {
      this.dataSource.data = data.sort((a,b) => a.name.localeCompare(b.name));
    })
  }

  loadLecturerTypes(){
    this.lecturerTypeService.getAllLecturerTypes().subscribe((data: ILecturerType[]) => {
      const lecturerTypeField = this.formConfig.fields.find(f => f.name === 'lecturerType') as any;

      if(lecturerTypeField){
        lecturerTypeField.options = data.map(lt => ({
          value: lt,
          label: lt.typeName,
          requiredHours: lt.requiredHours,
          lectureship: lt.lectureship
        }));

        lecturerTypeField.additionalFields = {
          requiredHours: true,
          lectureship: true
        };

      } else {
        console.error("'LecturerType' not found in formConfig");
      }
    })
  }

  onSave(data: any) {
    if (data.id) {
      this.lecturerService.updateLecturer(data.id, data).subscribe({
        next: (updated) => {
          console.log('Successfully updated:', updated);
          this.loadData();
        },
        error: (err) => {
          console.error('Error while updating:', err);
        }
      })
    }
    else{
      this.lecturerService.createLecturer(data).subscribe({
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

  onDelete(row: ILecturer) {
    if (row.id) {
      this.lecturerService.deleteLecturer(row.id).subscribe({
        next: () => {
          console.log('Successfully deleted:', row.id);
          this.loadData();
        },
        error: (err) => {
          console.log(err);
        }
      })
    }
  }
}

