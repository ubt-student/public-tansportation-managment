import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { GoogleMap, MapMarker } from '@angular/google-maps';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from '../services/user.service';
import { BiletaConfirmDialogComponent } from '../dialogs/bileta-confirm-dialog/bileta-confirm-dialog.component';
import { AdminService } from '../services/admin.service';
import { Municipality } from './models/municipality.model';
import { TicketService } from './ticket.service';
import { MapService } from './map/map.service';
import { Travel } from './models/travel.model';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss'],
})
export class HomePageComponent implements OnInit {
  municipalities: Municipality[] = [];
  municipalityFrom: Municipality;
  municipalityTo: Municipality;
  travels: Travel[];

  constructor(
    public userService: UserService,
    public dialog: MatDialog,
    public adminService: AdminService,
    public ticketService: TicketService,
    public mapService: MapService,
  ) {}

  ngOnInit(): void {
    this.municipalities = this.ticketService.getMunicipalities();
    this.ticketService
      .getRandomTravels()
      .subscribe((travels) => (this.travels = travels));
  }

  onMunicipalityFromSelected() {
    this.mapService.focusMunicipality(this.municipalityFrom.code);
    this.searchRoutes();
  }

  onMunicipalityToSelected() {
    this.mapService.focusMunicipality(this.municipalityTo.code);
    this.searchRoutes();
  }

  searchRoutes() {
    if (this.municipalityFrom != null && this.municipalityTo != null) {
      this.ticketService
        .getTravelsByMunicipalities(
          this.municipalityFrom.id,
          this.municipalityTo.id,
        )
        .subscribe((travels) => (this.travels = travels));
    }
  }

  swap() {
    // const swaping1 = { ...this.municipalityFrom };
    // const swaping2 = { ...this.municipalityTo };
    // this.municipalityFrom = { ...swaping2 } as Municipality;
    // this.municipalityTo = { ...swaping1 } as Municipality;
    // console.log(this.municipalityFrom);
    // console.log(this.municipalityTo);
  }

  getMunicipalityById(id: number) {
    return { ...this.municipalities.find((m) => m.id === id) };
  }

  dataSot: number = Date.now();

  center: any;
  toppings = new FormControl();
  toppings1 = new FormControl();

  openDialog(travel: Travel): void {
    const dialogRef = this.dialog.open(BiletaConfirmDialogComponent, {
      width: '480px',
      data: travel,
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed', result);
    });
  }
}
