import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  form: FormGroup
  constructor(private adminService: AdminService) {
    this.form = new FormGroup({
      company: new FormControl('', [Validators.minLength(2), Validators.required]),
      vendnisja: new FormControl('', [Validators.required]),
      destinacioni: new FormControl('', [Validators.required]),
      nisja: new FormControl('', [Validators.minLength(2), Validators.required, Validators.nullValidator]),
      arritja: new FormControl('', [Validators.minLength(2), Validators.required])
    })
    console.log('form', this.form)
   }

  ngOnInit(): void {
    console.log('form', this.form)
  }
  
  register = (): void => {
    this.adminService.register(this.form.controls.company.value,this.form.controls.vendnisja.value,this.form.controls.destinacioni.value,this.form.controls.nisja.value,this.form.controls.arritja.value).subscribe((res) =>{
      alert('u insertuan')
      console.log('e ka kalu insertin', res);
    }, (err) => {
      alert('nuk u insertuan')
    })
  }

  vendnisja = '';
  destinacioni = '';

  toppings = new FormControl();
  toppings1 = new FormControl();
  
  qytetetList: string[] = ['Prishtinë', 'Podujevë', 'Pejë', 'Gjakovë', 'Rahovec', 'Mitrovicë','Gjilan','Skenderaj'];
}
