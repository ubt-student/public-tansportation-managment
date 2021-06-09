import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  

  constructor(private router: Router,
    private auth: AuthService,
    public userService: UserService) {

  }

  public ol: any;

  ngOnInit(): void {
    if (this.auth.isTokenExpired()) {
      this.router.navigateByUrl('/login');
      console.log('expired');
    } else {
      this.userService.getUser().subscribe((user: any) => {
        this.userService.user = user;
      },(error) => {
          console.error(error);
      })
      console.log('going home');
      
      // this.router.navigateByUrl('/home');
      // this.router.navigateByUrl('');
    }
  }
  showFiller: boolean = false;

  logOut(){
    this.userService.user = null;
    this.auth.clearUser();
    this.router.navigateByUrl('/login');
  }
  myTickets(){
    this.router.navigateByUrl('/bileta');
  }

  typesOfShoes: string[] = ['Boots', 'Clogs', 'Loafers', 'Moccasins', 'Sneakers'];

  title = 'public-transportation-manager-app';
}
