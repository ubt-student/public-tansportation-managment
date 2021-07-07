import { Injectable } from '@angular/core';
import TileLayer from 'ol/layer/Tile';
import { Source, Vector, VectorTile, XYZ } from 'ol/source';
import { Vector as VectorLa } from 'ol/layer';
import Map from 'ol/Map';
import { Geo } from './models/geo.model';
import { HttpClient } from '@angular/common/http';
import VectorLayer from 'ol/layer/Vector';
import VectorSource from 'ol/source/Vector';
import GeoJSON from 'ol/format/GeoJSON';
import { Stroke, Style, Text, Fill, Icon, Circle } from 'ol/style';
import { environment } from 'src/environments/environment';
import { Route } from '../models/route.model';
import LineString from 'ol/geom/LineString';
import { Feature } from 'ol';
import Point from 'ol/geom/Point';

@Injectable({ providedIn: 'root' })
export class MapService {
  busPosition: VectorSource;
  currentRouteId: number;

  constructor(private http: HttpClient) {}

  private map: Map;

  initializeMap(map: Map) {
    this.map = map;
    this.addBusPosition();
    this.addGoogleOrtophoto();
    this.addMunicipalityLayer();
  }

  addBusPosition() {
    this.busPosition = new Vector({});
    const layer = new VectorLa({
      source: this.busPosition,
      className: 'BusPosition',
    });
    layer.setStyle(
      new Style({
        image: new Icon({
          src: '/assets/location.png',
        }),
      }),
    );
    layer.setZIndex(100);
    this.map.addLayer(layer);
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

  findRoute(route: Route) {
    this.currentRouteId = route.id;
    this.map.getLayers().forEach((layer) => {
      if (layer && layer.getClassName() === 'Route') {
        this.map.removeLayer(layer);
      }
    });
    this.http
      .get<Geo>(environment.mapUrl + 'route/geometry/' + route.id)
      .subscribe((geo) => {
        const routeLayer = this.getVectorLayer(
          geo,
          'Route',
          true,
          {
            LineString: new Style({
              stroke: new Stroke({
                color: 'yellow',
                width: 2,
              }),
            }),
          },
          false,
        );
        routeLayer.setZIndex(3);
        const feature = routeLayer.getSource().getFeatures()[0];
        (feature.getGeometry() as LineString)
          .getCoordinates()
          .forEach((coordinates, i) => {
            setTimeout(() => {
              if (this.currentRouteId === route.id) {
                this.busPosition.clear();
                const marker = new Feature({
                  geometry: new Point(coordinates),
                });
                this.busPosition.addFeature(marker);
              }
            }, i * 3000);
          });
        this.map.addLayer(routeLayer);
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
        true,
      );
      municipalityLayer.setZIndex(3);
      this.map.addLayer(municipalityLayer);
    });
  }

  focusMunicipality(municipalityCode: number) {
    this.getMunicipalityBordersByCode(municipalityCode).subscribe((geo) => {
      const municipalityLayer = this.getVectorLayer(
        geo,
        'Municipality',
        true,
        null,
        true,
      );
      const feature = municipalityLayer.getSource().getFeatures()[0];
      this.map
        .getView()
        .setCenter(this.centerOfExtenct(feature.getGeometry().getExtent()));
      this.map.getView().setZoom(12);
    });
  }

  getMunicipalityBorders() {
    return this.http.get<Geo>(environment.mapUrl + 'municipality/geometry');
  }

  getMunicipalityBordersByCode(municipalityCode: number) {
    return this.http.get<Geo>(
      environment.mapUrl + 'municipality/geometry/' + municipalityCode,
    );
  }

  getVectorLayer(
    geo: Geo,
    layerName: string,
    visible: boolean,
    style: any,
    layerText: boolean,
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

  centerOfExtenct(extent: any) {
    const X = extent[0] + (extent[2] - extent[0]) / 2;
    const Y = extent[1] + (extent[3] - extent[1]) / 2;
    return [X, Y];
  }
}
