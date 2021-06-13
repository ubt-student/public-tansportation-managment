import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SignupComponent } from './signup/signup.component';
import { HomePageComponent } from './home-page/home-page.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import { GoogleMapsModule } from '@angular/google-maps';
import {MatSelectModule} from '@angular/material/select';
import {MatDividerModule} from '@angular/material/divider';
import {MatDialogModule} from '@angular/material/dialog';
import { BiletaConfirmDialogComponent } from './dialogs/bileta-confirm-dialog/bileta-confirm-dialog.component';
import { BiletaDashboardComponent } from './tickets/bileta-dashboard/bileta-dashboard.component';
import { ConfirmDialogComponent } from './dialogs/confirm-dialog/confirm-dialog.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatListModule} from '@angular/material/list';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker'

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    HomePageComponent,
    BiletaConfirmDialogComponent,
    BiletaDashboardComponent,
    ConfirmDialogComponent,
    AdminDashboardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatSidenavModule,
    GoogleMapsModule,
    MatSelectModule,
    MatDividerModule,
    MatDialogModule,
    MatMenuModule,
    MatListModule,
    MatNativeDateModule,
    MatDatepickerModule,
  ],
  providers: [
   { provide: MAT_DATE_LOCALE, useValue: 'en-GB'}
  ],
  entryComponents:[BiletaConfirmDialogComponent,ConfirmDialogComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
