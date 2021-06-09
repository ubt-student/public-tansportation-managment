import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { GoogleMap, MapMarker } from '@angular/google-maps';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from '../services/user.service';
import { BiletaConfirmDialogComponent } from '../dialogs/bileta-confirm-dialog/bileta-confirm-dialog.component'

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {
  @ViewChild(GoogleMap, { static: false })
  map!: GoogleMap;
  constructor(public userService: UserService, public dialog: MatDialog) { }
  markers: any[] = []
  ngOnInit(): void {
    this.center = {
      lat: 42.6573824,
      lng: 21.168128,
    }
    console.log('center', this.center);
    
    this.addMarker()
  }

  center: any

  addMarker() {
    this.markers.push({
      position: {
        lat: this.center.lat + ((Math.random() - 0.5) * 2) / 10,
        lng: this.center.lng + ((Math.random() - 0.5) * 2) / 10,
      },
      label: {
        color: 'red',
        text: 'Marker label ' + (this.markers.length + 1),
      },
      title: 'Marker title ' + (this.markers.length + 1),
      options: { animation: google.maps.Animation.BOUNCE },
    })
  }
  toppings = new FormControl();
  toppings1 = new FormControl();

  vendnisja = '';
  destinacioni = '';

  toppingList: string[] = ['Prishtinë', 'Podujevë', 'Pejë', 'Gjakovë', 'Rahovec', 'Mitrovicë','Gjilan','Skenderaj'];
  toppingList1: string[] = ['Prishtinë', 'Podujevë', 'Pejë', 'Gjakovë', 'Rahovec', 'Mitrovicë','Gjilan','Skenderaj'];

  swap(){
    const item1 = this.vendnisja;
    this.vendnisja = this.destinacioni;
    this.destinacioni = item1;
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(BiletaConfirmDialogComponent, {
      width: '480px',
      data: {name: 'Emri', animal: 'Mbiemri'}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      
    });
  }
}


