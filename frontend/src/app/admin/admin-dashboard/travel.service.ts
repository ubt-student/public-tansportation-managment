import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MapService } from 'src/app/home-page/map/map.service';
import { Geo } from 'src/app/home-page/map/models/geo.model';
import { Route } from 'src/app/home-page/models/route.model';
import { Travel } from 'src/app/home-page/models/travel.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TravelService {
  constructor(private http: HttpClient, private mapService: MapService) {}

  getRoutes() {
    return this.http.get<Route[]>(environment.bookingUrl + 'route');
  }

  saveTravel(travel: Travel) {
    return this.http.post(environment.bookingUrl + 'travel', travel);
  }

  saveRoute(route: Route) {
    return this.http.post<Route>(environment.bookingUrl + 'route', route);
  }

  saveRouteGeom(id: number, geom: Geo) {
    return this.http.post(
      environment.mapUrl + 'route/geometry/' + id,
      geom?.features[0].geometry.coordinates,
    );
  }
}
