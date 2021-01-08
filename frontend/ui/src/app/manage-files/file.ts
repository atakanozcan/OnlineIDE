import {Project} from '../manage-project/project';

export interface File {
  project: Project;
  name: string;
  code: string;
}
