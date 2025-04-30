import ILecturerType from '../../lecturer-type/model/ILecturer-type';

export interface ILecturer {
  id?: string;
  name: string;
  lecturerType: ILecturerType;
}
