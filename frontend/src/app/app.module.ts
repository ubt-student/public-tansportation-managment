import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HomePageComponent } from './home-page/home-page.component';
import { BiletaConfirmDialogComponent } from './dialogs/bileta-confirm-dialog/bileta-confirm-dialog.component';
import { BiletaDashboardComponent } from './tickets/bileta-dashboard/bileta-dashboard.component';
import { ConfirmDialogComponent } from './dialogs/confirm-dialog/confirm-dialog.component';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { SharedModule } from './shared/shared.module';
import { AuthInterceptorService } from './auth/auth-interceptor.service';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MapComponent } from './home-page/map/map.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    BiletaConfirmDialogComponent,
    BiletaDashboardComponent,
    ConfirmDialogComponent,
    AdminDashboardComponent,
    MapComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'en-GB' },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true,
    },
  ],
  entryComponents: [BiletaConfirmDialogComponent, ConfirmDialogComponent],
  bootstrap: [AppComponent],
})
export class AppModule {}
