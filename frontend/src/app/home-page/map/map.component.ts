import { Component, OnInit } from '@angular/core';
import Map from 'ol/Map';
import View from 'ol/View';
import * as control from 'ol/control';
import { MapService } from './map.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss'],
})
export class MapComponent implements OnInit {
  map: Map;
  constructor(public mapService: MapService) {}

  ngOnInit(): void {
    this.map = new Map({
      target: 'map',
      layers: [],
      controls: control.defaults({
        attribution: false,
        zoom: false,
        rotate: false,
      }),
      view: new View({
        center: [2323473.436217629, 5252769.038063807],
        zoom: 9,
        minZoom: 8,
        maxZoom: 20,
      }),
    });
    this.mapService.initializeMap(this.map);
  }
}
