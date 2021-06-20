import { Injectable } from '@angular/core';
import TileLayer from 'ol/layer/Tile';
import { XYZ, TileWMS } from 'ol/source';
import Map from 'ol/Map';
import { Geo } from './models/geo.model';
import { HttpClient } from '@angular/common/http';
import VectorLayer from 'ol/layer/Vector';
import VectorSource from 'ol/source/Vector';
import GeoJSON from 'ol/format/GeoJSON';
import { Stroke, Style, Text, Fill } from 'ol/style';

@Injectable({ providedIn: 'root' })
export class MapService {
  constructor(private http: HttpClient) {}

  private map: Map;

  initializeMap(map: Map) {
    this.map = map;
    this.addGoogleOrtophoto();
    this.addMunicipalityLayer();
  }

  addGoogleOrtophoto() {
    const googleOrtophoto = this.getGoogleOrtophoto(true);
    this.map.addLayer(googleOrtophoto);
  }

  getGoogleOrtophoto(visible: boolean): TileLayer {
    return new TileLayer({
      source: new XYZ({
        url: 'http://mt0.google.com/vt/lyrs=s&hl=en&x={x}&y={y}&z={z}&s=Ga',
      }),
      className: 'Google ortophoto',
      visible,
    });
  }

  addMunicipalityLayer() {
    this.getMunicipalityBorders().subscribe((geo) => {
      const municipalityLayer = this.getVectorLayer(
        geo,
        'Municipality',
        true,
        {
          MultiPolygon: new Style({
            stroke: new Stroke({
              color: 'rgb(153,165,0)',
              width: 4,
            }),
            text: new Text({
              font: '14px Calibri,sans-serif',
              fill: new Fill({
                color: '#fff',
              }),
              stroke: new Stroke({
                color: '#000',
                width: 3,
              }),
            }),
            fill: new Fill({
              color: 'rgba(157, 198, 246, 0.0)',
            }),
          }),
        },
        true
      );
      municipalityLayer.setZIndex(3);
      this.map.addLayer(municipalityLayer);
    });
  }

  getMunicipalityBorders() {
    return this.http.get<Geo>('http://localhost:8081/map/municipality/geometry');
  }

  getVectorLayer(
    geo: Geo,
    layerName: string,
    visible: boolean,
    style: any,
    layerText: boolean
  ): VectorLayer {
    const vectorSource = new VectorSource({
      features: new GeoJSON().readFeatures(geo),
    });

    const styleFunction = (feature: any) => {
      if (layerText) {
        style[feature.getGeometry().getType()]
          .getText()
          .setText(this.stringDivider(feature.getId().toString(), 12, '\n'));
      }
      return style[feature.getGeometry().getType()];
    };

    return new VectorLayer({
      source: vectorSource,
      style: styleFunction,
      className: layerName,
      visible,
    });
  }

  stringDivider(str: string, width: number, spaceReplacer: string): any {
    if (str.length > width) {
      let p = width;
      while (p > 0 && str[p] !== ' ' && str[p] !== ' ') {
        p--;
      }
      if (p > 0) {
        let left;
        if (str.substring(p, p + 1) === ' ') {
          left = str.substring(0, p + 1);
        } else {
          left = str.substring(0, p);
        }
        const right = str.substring(p + 1);
        return (
          left + spaceReplacer + this.stringDivider(right, width, spaceReplacer)
        );
      }
    }
    return str;
  }
}
