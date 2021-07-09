import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TravelService } from 'src/app/admin/admin-dashboard/travel.service';
import { MapService } from 'src/app/home-page/map/map.service';
import { Route } from 'src/app/home-page/models/route.model';
import { TicketService } from 'src/app/home-page/ticket.service';

@Component({
  selector: 'app-route-map-dialog',
  templateUrl: './route-map-dialog.component.html',
  styleUrls: ['./route-map-dialog.component.scss'],
})
export class RouteMapDialogComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<RouteMapDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Route,
    private travelService: TravelService,
    private mapService: MapService,
  ) {}

  ngOnInit() {}

  saveRoute() {
    this.travelService.saveRoute(this.data).subscribe((route) => {
      const geom = this.mapService.getConstructedRouteGeom();
      this.travelService.saveRouteGeom(route.id, geom).subscribe(() => {
        this.onNoClick();
      });
    });
  }

  onNoClick() {
    this.dialogRef.close();
  }
}
