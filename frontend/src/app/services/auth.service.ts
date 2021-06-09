import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { 
    this.getToken();
  }

  userToken: any;

  saveToken(token: string): void {
    localStorage.setItem('userToken', token);
    const date = new Date();

    localStorage.setItem('expireTime', new Date(date.setHours(date.getHours() + 24)).getTime().toString())
  }

  getToken(): void {
    this.userToken = localStorage.getItem('userToken')?.toString();
  }

  isTokenExpired(): boolean {
    let date = new Date(Number(localStorage.getItem('expireTime'))).getTime();
    let dateNow = new Date().getTime();

    console.log('expire time', dateNow > date);
    return dateNow > date;
  }
  
  clearUser(){
    this.userToken = null;
    localStorage.setItem('userToken','');
    localStorage.setItem('expireTime', '');
  }

}
