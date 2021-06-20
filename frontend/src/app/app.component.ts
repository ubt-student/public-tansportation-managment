import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth/auth.service';
import { User } from './auth/models/user.model';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  user: User;

  constructor(private router: Router,
    private authService: AuthService,
    public userService: UserService) {

  }

  public ol: any;

  ngOnInit(): void {
    this.authService.user.subscribe(user => {
      this.user = user;
    })
    this.authService.autoLogin();
    // if (this.auth.isTokenExpired()) {
    //   this.router.navigateByUrl('/login');
    //   console.log('expired');
    // } else {
    //   this.userService.getUser().subscribe((user: any) => {
    //     this.userService.user = user;
    //   },(error) => {
    //       console.error(error);
    //   })
    //   console.log('going home');

    //   // this.router.navigateByUrl('/home');
    //   // this.router.navigateByUrl('');
    // }
  }
  showFiller: boolean = false;

  myTickets(){
    this.router.navigateByUrl('/bileta');
  }

  logout(){
    this.authService.logout();
    this.router.navigateByUrl('/auth/login');
  }

  typesOfShoes: string[] = ['Boots', 'Clogs', 'Loafers', 'Moccasins', 'Sneakers'];

  title = 'public-transportation-manager-app';
}
