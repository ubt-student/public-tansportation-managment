import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private http: HttpClient) { }
  signup = (firstName: string, lastName: string, email: string, password: string, confirmPassword: string): Observable<any> => {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json, text/plain, */*');
    headers.append('Accept', 'application/json')
    return this.http.post('http://localhost:8181/signup', {
      firstName: firstName,
      lastName: lastName,
      email: email,
      password: password,
      confirmPassword: confirmPassword
    }, { headers: headers });
  }
}
