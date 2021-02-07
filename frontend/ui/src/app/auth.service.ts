import { Injectable } from '@angular/core';
import { ReplaySubject } from 'rxjs';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

const LOGIN_PATH = '/login';
const LOGOUT_PATH = '/logout';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authenticated$: ReplaySubject<boolean> = new ReplaySubject<boolean>(1);

  public get authenticated(): ReplaySubject<boolean> {
    return this.authenticated$;
  }

  constructor(private httpClient: HttpClient,
              private router: Router) {
    this.checkAuthentication();
  }

  public checkAuthentication(): void {
    this.httpClient.get<boolean>('/authenticated')
      .subscribe((authenticated) => {
        console.log(authenticated);
        this.authenticated$.next(authenticated);
      }, (err) => {
        this.authenticated$.next(false);
      });
  }

  public  login():void {
    window.location.href = `${window.location.origin}${LOGIN_PATH}`; // handled by backend
  }

  public logout(): void {
    this.httpClient.post(LOGOUT_PATH, {})
      .subscribe(() => {
        this.router.navigateByUrl('/');
        this.checkAuthentication();
      });
  }
}
