import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient, public auth: AuthService) { }
  register = (company: string, vendnisja: string, destinacioni: string, nisja: Date, arritja: Date): Observable<any> => {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json, text/plain, */*');
    headers.append('Accept', 'application/json')
    return this.http.post('http://localhost:8181/lines', {
      company: company,
      vendnisja: vendnisja,
      destinacioni: destinacioni,
      nisja: nisja,
      arritja: arritja
    }, { headers: headers });
  }
  admin: any;

  getSchedules = (): Observable<any> => {
    const headers = new HttpHeaders()
    .set('Content-Type', 'application/json, text/plain, */*')
    .set('Accept', 'application/json')
    .set('Authorization', this.auth.userToken);
    console.log('headers',headers);

    return this.http.get('http://localhost:8181/lines', 
    { headers: headers });
  }
}
