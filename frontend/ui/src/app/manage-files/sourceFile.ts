import {Project} from '../manage-project/project';

export interface SourceFile {
  project?: Project;
  name: string;
  code: string;
  stdout?: string;
  stderr?: string;
  compilable?: boolean;
}
