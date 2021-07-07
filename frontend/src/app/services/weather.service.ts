import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Weather } from '../models/weather.interface';

@Injectable({
  providedIn: 'root',
})
export class WeatherService {
  constructor(private http: HttpClient) {}

  getCurrentWeather() {
    return this.http.get<Weather>(
      'https://cors-anywhere.herokuapp.com/http://api.openweathermap.org/data/2.5/weather?id=786714&appid=' +
        environment.openWeatherApi +
        '&units=metric',
    );
  }

  getWeatherIcon(icon: string) {
    return 'http://openweathermap.org/img/wn/' + icon + '@2x.png';
  }
}
