import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpHeaders,
  HttpResponse,
} from '@angular/common/http';
import { AuthService } from './auth.service';
import { take, exhaustMap, tap, catchError } from 'rxjs/operators';
import { of, throwError } from 'rxjs';

@Injectable()
export class AuthInterceptorService implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    return this.authService.user.pipe(
      take(1),
      exhaustMap((user) => {
        if (!user) {
          return next.handle(req);
        }

        const modifiedReq = req.clone({
          headers: new HttpHeaders().append('Authorization', 'Bearer ' + user.jwt),
        });
        return next.handle(modifiedReq).pipe(
          tap((event) => {
            if (event instanceof HttpResponse) {
              const res = event as HttpResponse<any>;
              const token = res.headers.get('Authorization');
              this.authService.changeUserToken(token);
            }
            return of(event);
          }),
          catchError((error) => {
            if (
              error.status === 401 &&
              error.headers.get('WWW-Authenticate') === 'Session time out'
            ) {
              const errorMessage = 'SESSION_TIMEOUT';
              this.authService.error.next(errorMessage);
            }

            if (
              error.status === 500 ||
              error.status === 404 ||
              error.status === 0
            ) {
              const errorMessage = 'ERROR';
              // this.authService.error.next(errorMessage);
            }

            return throwError(error);
          })
        );
      })
    );
  }
}
