import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth/auth.service';
import { User } from './auth/models/user.model';
import { Weather } from './models/weather.interface';
import { UserService } from './services/user.service';
import { WeatherService } from './services/weather.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  user: User;
  weather: Weather;
  url: string;
  constructor(
    private weatherService: WeatherService,
    private router: Router,
    private authService: AuthService,
    public userService: UserService,
  ) {}

  public ol: any;

  ngOnInit(): void {
    this.authService.user.subscribe((user) => {
      this.user = user;
    });
    this.authService.autoLogin();
    this.weatherService.getCurrentWeather().subscribe((weather) => {
      this.weather = weather;
      this.url = this.weatherService.getWeatherIcon(
        this.weather.weather[0].icon,
      );
    });
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

  myTickets() {
    this.router.navigateByUrl('/bileta');
  }

  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/auth/login');
  }

  typesOfShoes: string[] = [
    'Boots',
    'Clogs',
    'Loafers',
    'Moccasins',
    'Sneakers',
  ];

  title = 'public-transportation-manager-app';
}
