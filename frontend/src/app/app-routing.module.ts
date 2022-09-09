import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './about-us/about-us.component';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { AuthGuard } from './auth/auth.guard';
import { RoleGuard } from './auth/role.guard';
import { HomePageComponent } from './home-page/home-page.component';
import { MunicipalitiesResolver } from './home-page/resolvers/municipalities.resolver';
import { BiletaDashboardComponent } from './tickets/bileta-dashboard/bileta-dashboard.component';

const routes: Routes = [
  { path: '', redirectTo: '/auth/login', pathMatch: 'full' },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: 'home',
    resolve: [MunicipalitiesResolver],
    canActivate: [AuthGuard],
    component: HomePageComponent,
  },
  {
    path: 'bileta',
    resolve: [MunicipalitiesResolver],
    canActivate: [AuthGuard],
    component: BiletaDashboardComponent,
  },
  {
    path: 'about',
    resolve: [MunicipalitiesResolver],
    canActivate: [AuthGuard],
    component: AboutUsComponent,
  },
  {
    path: 'admin',
    canActivate: [AuthGuard, RoleGuard],
    resolve: [MunicipalitiesResolver],
    component: AdminDashboardComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
