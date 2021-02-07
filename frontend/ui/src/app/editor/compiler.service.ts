import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {SourceFile} from "../manage-files/sourceFile";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CompilerService {
  compilerUrl = "/compiler"
  constructor(private http: HttpClient) {}

  compile(file: SourceFile): Observable<SourceFile>{
    const url = `${this.compilerUrl}/compile`;
    return this.http.post<SourceFile>(url, file,
      httpOptions, ).pipe();
  }
}
