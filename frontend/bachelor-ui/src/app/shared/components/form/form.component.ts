import {Component, EventEmitter, inject, Inject, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {NgForOf, NgIf} from '@angular/common';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSelectModule} from '@angular/material/select';
import {StudyCourseService} from '../../../study-course/services/study-course.service';
import {ISemester} from '../../../study-course/model/ISemester';
import {NgxMaskDirective} from 'ngx-mask';
import {MatIconModule} from '@angular/material/icon';


@Component({
  selector: 'app-form',
  standalone: true,
  imports: [MatDialogModule, MatFormFieldModule, MatInputModule, ReactiveFormsModule,
    MatButtonModule, NgForOf, MatCheckboxModule, NgIf, MatSelectModule, NgxMaskDirective, MatIconModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})

export class FormComponent {
  protected studyCourseService: StudyCourseService = inject(StudyCourseService);
  form: FormGroup;
  initialData: any;
  isEditMode: boolean = false;

  @Output() sendSemesters = new EventEmitter<ISemester[]>();

  constructor(
    private readonly fb: FormBuilder,
    private readonly dialogRef: MatDialogRef<FormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.isEditMode = data.controls && Object.keys(data.controls).length > 0;
    this.form = this.fb.group({});
    this.initialData = {...data.controls};
    for (const field of data.fields) {
      this.form.addControl(field.name, this.fb.control(data.controls?.[field.name] ?? ''));

      if (field.name === 'studyCourse') {

        const studyCourseControl = this.form.get('studyCourse');

        studyCourseControl?.valueChanges.subscribe(value => {
          this.studyCourseService.getSemestersByStudyCourse(value.id).subscribe(semesters => {
            this.sendSemesters.emit(semesters);
          });
        });

      }
    }
  }


  onCancel() : void{
    this.dialogRef.close();
  }

  onSave(): void {
    if (this.form.valid) {
      this.dialogRef.close(this.form.value); // Закрытие диалога и передача данных
    } else {
      // Здесь можно добавить обработку ошибки, если форма невалидна
      console.log('Form is invalid');
    }
  }

  onClear(): void {
    if(this.isEditMode) {
      this.resetToInitialValues();
    } else {
      this.form.reset();
    }
  }

  resetToInitialValues(){
    this.form.patchValue(this.initialData);
  }

}
