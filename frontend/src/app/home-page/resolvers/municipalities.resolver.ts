import { Injectable, PLATFORM_ID, Inject } from '@angular/core';
import {
  Resolve,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
} from '@angular/router';
import { Municipality } from '../models/municipality.model';
import { TicketService } from '../ticket.service';

@Injectable({ providedIn: 'root' })
export class MunicipalitiesResolver
  implements Resolve<Municipality[] | Municipality>
{
  constructor(private ticketService: TicketService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const municipalities = this.ticketService.getMunicipalities();

    if (municipalities.length === 0) {
      return this.ticketService.fetchMunicipalities();
    } else {
      return municipalities;
    }
  }
}
