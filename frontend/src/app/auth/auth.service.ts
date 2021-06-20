import { Injectable } from '@angular/core';
import { BehaviorSubject, throwError, Subject, interval } from 'rxjs';
import {
  tap,
  take,
  map,
  catchError,
  mergeMap,
  delay,
  timeout,
} from 'rxjs/operators';
import { User } from './models/user.model';
import { HttpClient } from '@angular/common/http';
import { Authentication } from './models/authentication.interface';
import { Router } from '@angular/router';
import { CreateUser } from './models/create-user.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  user = new BehaviorSubject<User>(null);
  error = new Subject<string>();
  private tokenExpirationTimer: any;

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string) {
    const authentication: Authentication = { email, password };
    return this.http
      .post<User>('http://localhost:8080/auth/login', authentication)
      .pipe(
        delay(500),
        timeout(20000),
        catchError((errorResp) =>
          interval(500).pipe(
            mergeMap(() => {
              if (errorResp.name === 'TimeoutError') {
                return throwError('timeout');
              }
              return throwError(errorResp.headers.get('WWW-Authenticate'));
            })
          )
        ),
        tap((user) => {
          const expiresIn = 24 * 60 * 60 * 1000;
          const expirationDate = new Date(new Date().getTime() + expiresIn);
          user.expirationDate = expirationDate;
          this.user.next(user);
          this.autoLogout(expiresIn);
          localStorage.setItem('userData', JSON.stringify(user));
        })
      );
  }

  signup(email: string, firstname: string, lastname: string, password: string, avatar: string) {
    const createUser: CreateUser = { email, firstname, lastname, password, avatar };
    return this.http
      .post<User>('http://localhost:8080/auth/register', createUser)
      .pipe(
        delay(500),
        timeout(20000),
        catchError((errorResp) =>
          interval(500).pipe(
            mergeMap(() => {
              if (errorResp.name === 'TimeoutError') {
                return throwError('timeout');
              }
              return throwError("email-unique");
            })
          )
        ),
        tap((user) => {
          const expiresIn = 24 * 60 * 60 * 1000;
          const expirationDate = new Date(new Date().getTime() + expiresIn);
          user.expirationDate = expirationDate;
          this.user.next(user);
          this.autoLogout(expiresIn);
          localStorage.setItem('userData', JSON.stringify(user));
        })
      );
  }

  autoLogin() {
    const userData: {
      email: string;
      firstname: string;
      lastname: string;
      avatar: string;
      jwt: string;
      expirationDate: Date;
      admin: boolean;
    } = JSON.parse(localStorage.getItem('userData'));
    if (!userData) {
      return;
    }

    const loadedUser = new User(
      userData.email,
      userData.firstname,
      userData.lastname,
      userData.avatar,
      userData.jwt,
      new Date(userData.expirationDate),
      userData.admin
    );

    if (loadedUser.jwt) {
      this.user.next(loadedUser);
      const expirationDuration =
        new Date(userData.expirationDate).getTime() - new Date().getTime();
      this.autoLogout(expirationDuration);
    }
  }

  autoLogout(expirationDuration: number) {
    this.tokenExpirationTimer = setTimeout(() => {
      this.logout();
    }, expirationDuration);
  }

  changeUserToken(token: string) {
    this.user.pipe(
      take(1),
      map((user) => {
        user.jwt = token;
        this.user.next(user);
      })
    );
  }

  logout() {
    this.user.next(null);
    this.error.next(null);
    this.router.navigate(['/login']);
    localStorage.removeItem('userData');
    if (this.tokenExpirationTimer) {
        clearTimeout(this.tokenExpirationTimer);
    }
    this.tokenExpirationTimer = null;
  }
}
