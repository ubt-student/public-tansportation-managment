import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(public http: HttpClient,
    public auth: AuthService
    ) { }

  user: any;

  getUser = (): Observable<any> => {
    const headers = new HttpHeaders()
    .set('Content-Type', 'application/json, text/plain, */*')
    .set('Accept', 'application/json')
    .set('Authorization', this.auth.userToken);
    console.log('headers',headers);
    
    return this.http.get('http://localhost:8181/user', 
    { headers: headers });
  }
}
