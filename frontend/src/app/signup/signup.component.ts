import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SignupService } from '../services/signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  form: FormGroup
  constructor(private signupService: SignupService) { 
    this.form = new FormGroup({
      name: new FormControl('', [Validators.minLength(2), Validators.required]),
      lastName: new FormControl('', [Validators.minLength(2), Validators.required]),
      email: new FormControl('', [Validators.minLength(2), Validators.email, Validators.required, Validators.nullValidator]),
      password: new FormControl('', [Validators.minLength(2), Validators.required, Validators.nullValidator]),
      confirmPassword: new FormControl('', [Validators.minLength(2), Validators.required])
    })
  }

  ngOnInit(): void {
  }
  signup = (): void => {
    this.signupService.signup(this.form.controls.name.value,this.form.controls.lastName.value,this.form.controls.email. value,this.form.controls.password.value,this.form.controls.confirmPassword.value).subscribe((res) =>{
      alert('u insertuan')
      console.log('e ka kalu insertin', res);
    }, (err) => {
      alert('nuk u insertuan')
    })
  }

}
