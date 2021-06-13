import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router'
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup
  constructor(
    private loginService: LoginService,
    private router: Router,
    private auth: AuthService,
    private userService: UserService 
    ) {
    this.form = new FormGroup({
      email: new FormControl('', [Validators.minLength(2), Validators.email, Validators.required]),
      password: new FormControl('', [Validators.minLength(2), Validators.required])
    })
    this.auth.isTokenExpired();
   }

  ngOnInit(): void {
  }

  loginCredentials = {
    email: '',
    password: ''
  }
  
     
  
  login = (): void => {
    this.loginService.login(this.form.controls.email.value,
      this.form.controls.password.value).subscribe((res) => {
        console.log('res', res);
        this.auth.saveToken(res.token);
        this.router.navigateByUrl('/home');
        this.userService.getUser().subscribe((user: any) => {
          this.userService.user = user;
        },(error) => {
            console.error(error);
        })
        
    }, (err) => {
      alert('keq')
    })
  }
  register = (): void =>{
    this.router.navigateByUrl('/signup');
  }

}
