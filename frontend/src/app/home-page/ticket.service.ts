import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Ticket } from '../dialogs/bileta-confirm-dialog/models/ticket.model';
import { MapService } from './map/map.service';
import { Municipality } from './models/municipality.model';
import { Travel } from './models/travel.model';

@Injectable({
  providedIn: 'root',
})
export class TicketService {
  private municipalities: Municipality[] = [];

  constructor(private http: HttpClient, private mapService: MapService) {}

  getRandomTravels() {
    return this.http.get<Travel[]>(environment.bookingUrl + 'travel');
  }

  registerTicket(ticket: Ticket) {
    return this.http.post(environment.bookingUrl + 'ticket', ticket);
  }

  getTicketsByUserEmail(email: string) {
    let params = new HttpParams().set('email', email);
    return this.http.get<Ticket[]>(environment.bookingUrl + 'ticket', {
      params,
    });
  }

  getTravelsByMunicipalities(municipalityFrom: number, municipalityTo: number) {
    return this.http
      .get<Travel[]>(
        environment.bookingUrl +
          'travel/' +
          municipalityFrom +
          '/' +
          municipalityTo,
      )
      .pipe(
        tap((travels) => {
          if (travels.length > 0) {
            const route = travels[0].route;
            this.mapService.findRoute(route);
          }
        }),
      );
  }

  fetchMunicipalities() {
    return this.http
      .get<Municipality[]>(environment.mapUrl + 'municipality')
      .pipe(
        tap((municipalities) => {
          municipalities.map((municipality) => {
            this.municipalities.push(
              Object.assign(new Municipality(), municipality),
            );
          });
        }),
      );
  }

  getRates(sign: string) {
    return this.http.get(
      'https://cors-anywhere.herokuapp.com/https://free.currconv.com/api/v7/convert?q=' +
        sign +
        '&compact=ultra&apiKey=e426a2aa776b1f6d91e9',
    );
  }

  getMunicipalities() {
    return this.municipalities.slice();
  }
}
