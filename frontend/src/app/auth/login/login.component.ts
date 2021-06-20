import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  error: string;
  constructor(private router: Router, private authService: AuthService) {
    this.form = new FormGroup({
      email: new FormControl('', [
        Validators.minLength(2),
        Validators.email,
        Validators.required,
      ]),
      password: new FormControl('', [
        Validators.minLength(2),
        Validators.required,
      ]),
    });
  }

  ngOnInit() {}

  login() {
    const email = this.form.controls.email.value;
    const password = this.form.controls.password.value;
    this.authService.login(email, password).subscribe(
      () => {
        this.router.navigate(['/home']);
      },
      () => {
        this.error = "Credentials are incorrect!"
      }
    );
  }

  register() {
    this.router.navigateByUrl('/auth/signup');
  }
}
