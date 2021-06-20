import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  form: FormGroup;
  imageError: string;
  isImageSaved: boolean;
  cardImageBase64: string;
  error: string;

  constructor(private router: Router, private authService: AuthService) {
    this.form = new FormGroup({
      name: new FormControl('', [Validators.minLength(2), Validators.required]),
      lastName: new FormControl('', [
        Validators.minLength(2),
        Validators.required,
      ]),
      email: new FormControl('', [
        Validators.minLength(2),
        Validators.email,
        Validators.required,
        Validators.nullValidator,
      ]),
      password: new FormControl('', [
        Validators.minLength(3),
        Validators.required,
        Validators.nullValidator,
      ]),
      confirmPassword: new FormControl('', [
        Validators.minLength(3),
        Validators.required,
      ]),
    });
  }

  ngOnInit() {
    this.error = null;
    this.imageError = null;
    this.isImageSaved = null;
    this.cardImageBase64 = null;
    this.form.reset();
  }

  signup() {
    this.error = null;
    if (
      this.form.controls.password.value !==
      this.form.controls.confirmPassword.value
    ) {
      this.error = "Password doesn't match";
      return;
    }

    if (!this.cardImageBase64) {
      this.error = 'Avatar is not added';
      return;
    }
    const email = this.form.controls.email.value;
    const firstname = this.form.controls.name.value;
    const lastname = this.form.controls.lastName.value;
    const password = this.form.controls.password.value;
    const avatar = this.cardImageBase64;

    this.authService
      .signup(email, firstname, lastname, password, avatar)
      .subscribe(
        () => {
          this.router.navigate(['/home']);
        },
        () => {
          this.error = 'Email is not unique!';
        }
      );
  }

  fileChangeEvent(fileInput: any) {
    this.imageError = null;
    if (fileInput.target.files && fileInput.target.files[0]) {
      const max_size = 20971520;
      const allowed_types = ['image/png', 'image/jpeg'];
      const max_height = 15200;
      const max_width = 25600;

      if (fileInput.target.files[0].size > max_size) {
        this.imageError = 'Maximum size allowed is ' + max_size / 1000 + 'Mb';
        return;
      }
      if (
        !allowed_types.some((value) => value === fileInput.target.files[0].type)
      ) {
        this.imageError = 'Only Images are allowed ( JPG | PNG )';
        return;
      }
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const image = new Image();
        image.src = e.target.result;
        image.onload = () => {
          const img_height = image.height;
          const img_width = image.width;

          if (img_height > max_height && img_width > max_width) {
            this.imageError =
              'Maximum dimentions allowed ' +
              max_height +
              '*' +
              max_width +
              'px';
            return;
          } else {
            const imgBase64Path = e.target.result;
            this.cardImageBase64 = imgBase64Path;
            this.isImageSaved = true;
          }
        };
      };

      reader.readAsDataURL(fileInput.target.files[0]);
    }
  }

  removeImage() {
    this.cardImageBase64 = null;
    this.isImageSaved = false;
  }
}
