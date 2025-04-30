import {AfterViewInit, Component, inject} from '@angular/core';
import {LecturerTypeService} from './services/lecturer-type.service';
import {TableComponent} from '../shared/components/table/table.component';
import ILecturerType from './model/ILecturer-type';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-lecturer-type',
  standalone: true,
  imports: [TableComponent],
  templateUrl: './lecturer-type.component.html',
  styleUrl: './lecturer-type.component.css'
})
export class LecturerTypeComponent implements AfterViewInit {
  //Service Class
  protected lecturerTypeService: LecturerTypeService = inject(LecturerTypeService);

  title: string = 'Lecturer Type';
  dataSource = new MatTableDataSource<ILecturerType>();

  myColumns = [
    {columnDef: 'typeName', header: 'Type Name', cell: (row: ILecturerType) => row.typeName},
    {columnDef: 'requiredHours', header: 'Required Hours', cell: (row: ILecturerType) => row.requiredHours.toString()},
    {columnDef: 'lectureship', header: 'Lectureship', cell: (row: ILecturerType) => (row.lectureship ? 'Yes' : 'No')},
  ];
  displayedColumns = [...this.myColumns.map(col => col.columnDef), 'action'];

  formConfig = {
    fields: [
      { name: 'typeName', type: 'text', label: 'Type Name', required: true},
      { name: 'requiredHours', type: 'number', label: 'Required Hours', required: true},
      { name: 'lectureship', type: 'checkbox', label: 'Lectureship',}
    ],
    controls: {
      typeName: null,
      requiredHours: null,
      lectureship: false,
    }
  }

  ngAfterViewInit() {
    this.loadData();
  }

  loadData() {
    this.lecturerTypeService.getAllLecturerTypes().subscribe((data: ILecturerType[]) => {
      this.dataSource.data = data.sort((a,b) => a.typeName.localeCompare(b.typeName));
    });
  }

  // Метод для сохранения/изменения данных
  onSave(data: any) {
    if (data.id) {
      this.lecturerTypeService.updateLecturerType(data.id, data).subscribe({
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
    this.lecturerTypeService.createLecturerType(data).subscribe({
      next: (created) => {
        console.log('Successfully created:', created);
        this.loadData(); // Обновляем данные в таблице
      },
      error: (err) => {
        console.error('Error while creating:', err);
      }
    });
    }
  }

  onDelete(row: ILecturerType) {
    if (row.id) {
      this.lecturerTypeService.deleteLecturerType(row.id).subscribe({
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
