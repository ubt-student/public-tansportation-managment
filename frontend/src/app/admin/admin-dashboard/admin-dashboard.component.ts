import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { RouteMapDialogComponent } from 'src/app/dialogs/route-map-dialog/route-map-dialog.component';
import { Municipality } from 'src/app/home-page/models/municipality.model';
import { Route } from 'src/app/home-page/models/route.model';
import { Travel } from 'src/app/home-page/models/travel.model';
import { TicketService } from 'src/app/home-page/ticket.service';
import { AdminService } from 'src/app/services/admin.service';
import { TravelService } from './travel.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss'],
})
export class AdminDashboardComponent implements OnInit {
  municipalities: Municipality[] = [];
  routes: Route[] = [];
  formLine: FormGroup;
  formTravel: FormGroup;
  travelSuccess = false;
  lineSuccess = false;
  constructor(
    private travelService: TravelService,
    private ticketService: TicketService,
    public dialog: MatDialog,
  ) {
    this.formLine = new FormGroup({
      departure: new FormControl('', [Validators.required]),
      arrival: new FormControl('', [Validators.required]),
    });
    this.formTravel = new FormGroup({
      agency: new FormControl('', [
        Validators.minLength(2),
        Validators.required,
      ]),
      line: new FormControl('', [Validators.required]),
      departureTime: new FormControl('', [
        Validators.minLength(2),
        Validators.required,
        Validators.nullValidator,
      ]),
      arrivalTime: new FormControl('', [
        Validators.minLength(2),
        Validators.required,
      ]),
      price: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
    this.travelSuccess = false;
    this.lineSuccess = false;
    this.municipalities = this.ticketService.getMunicipalities();
    this.travelService
      .getRoutes()
      .subscribe((routes) => (this.routes = routes));
  }

  getMunicipalityById(id: number) {
    return { ...this.municipalities.find((m) => m.id === id) };
  }

  registerLine() {
    const route: Route = new Route();
    route.municipalityIdFrom = this.formLine.controls.departure.value;
    route.municipalityIdTo = this.formLine.controls.arrival.value;

    const dialogRef = this.dialog.open(RouteMapDialogComponent, {
      width: '150vh',
      data: route,
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.lineSuccess = true;
      this.formLine.reset();
      Object.keys(this.formLine.controls).forEach((key) => {
        this.formLine.controls[key].setErrors(null);
      });
    });
  }

  registerTravel() {
    const travel: Travel = new Travel();
    travel.agency = this.formTravel.controls.agency.value;
    travel.departTime = this.formTravel.controls.departureTime.value;
    travel.arrivalTime = this.formTravel.controls.arrivalTime.value;
    travel.price = +this.formTravel.controls.price.value;
    const route = this.routes.find(
      (r) => r.id === this.formTravel.controls.line.value,
    );
    travel.route = route;
    this.travelService.saveTravel(travel).subscribe(() => {
      this.travelSuccess = true;
      this.formTravel.reset();
      Object.keys(this.formTravel.controls).forEach((key) => {
        this.formTravel.controls[key].setErrors(null);
      });
    });
  }
}
