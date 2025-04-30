export interface ICourse {
  id?: string;
  courseName: string;
  weeklyLectureHours: number;
  weeklyExerciseHours: number;
  isMandatory: boolean;
  creditPoints: number;
}
