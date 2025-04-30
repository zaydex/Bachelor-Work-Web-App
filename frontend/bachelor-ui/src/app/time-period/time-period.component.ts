import {AfterViewInit, Component, inject} from '@angular/core';
import {TableComponent} from '../shared/components/table/table.component';
import {TimePeriodService} from './services/time-period.service';
import {ITimePeriod} from './model/ITimePeriod';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-time-period',
  standalone: true,
  imports: [TableComponent],
  templateUrl: './time-period.component.html',
  styleUrl: './time-period.component.css'
})
export class TimePeriodComponent implements AfterViewInit{
  protected timePeriodService: TimePeriodService = inject(TimePeriodService);

  title: string = 'Time Period';
  dataSource: MatTableDataSource<ITimePeriod> = new MatTableDataSource<ITimePeriod>();
  myColumns = [
    {columnDef: 'academicYear', header: 'Academic Year', cell: (row: ITimePeriod) => row.academicYear},
  ];
  displayedColumns = [...this.myColumns.map(col => col.columnDef), 'action'];
  formConfig = {
    fields: [
      { name: 'academicYear', type: 'text', label: 'Academic Year (ex: SS 18/19)',
        placeholder: 'WS YY-YY', required: true, validation: true, mask: 'AA 00/00', dropSpecialCharacters: false },
    ],
    controls: {
      academicYear: null
    }
  }

  ngAfterViewInit() {
    this.loadData();
  }


  loadData() {
    this.timePeriodService.getAllTimePeriods().subscribe((data: ITimePeriod[]) => {
      this.dataSource.data = data.sort((a,b) => a.academicYear.localeCompare(b.academicYear));
    });
  }

  onSave(data: any) {
    if (data.id) {
      this.timePeriodService.updateTimePeriod(data.id, data).subscribe({
        next: (updated) => {
          console.log('Successfully updated:', updated);
          this.loadData();
        },
        error: (err) => {
          console.error('Error while updating:', err);
        },
      })
    }
    else{
      this.timePeriodService.createTimePeriod(data).subscribe({
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

  onDelete(timePeriod: ITimePeriod) {
    if (timePeriod.id) {
      this.timePeriodService.deleteTimePeriod(timePeriod.id).subscribe({
        next: () => {
          console.log('Successfully deleted:', timePeriod.id);
          this.loadData();
        },
        error: (err) => {
          console.error('Error while deleting:', err);
        },
      });
    } else {
      console.error('ID not found for the Time Period:', timePeriod);}
  }
}
