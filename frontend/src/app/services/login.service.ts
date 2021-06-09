import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login = (email: string, password: string): Observable<any> => {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json, text/plain, */*');
    headers.append('Accept', 'application/json');
    return this.http.post('http://localhost:8181/login', {
      email: email,
      password: password
    }, { headers: headers });
  }
}
