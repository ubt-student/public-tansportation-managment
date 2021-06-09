import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RezervoBiletenService {

  constructor(private http: HttpClient) {}
  save = (bileta: any): Observable<any> => {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json, text/plain, */*');
    headers.append('Accept', 'application/json')
    return this.http.post('http://localhost:8181/bileta',{
      bileta: bileta
    }, { headers: headers });
  }
}
