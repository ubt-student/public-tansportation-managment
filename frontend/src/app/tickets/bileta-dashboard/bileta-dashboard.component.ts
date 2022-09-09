import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/auth/auth.service';
import { User } from 'src/app/auth/models/user.model';
import { Ticket } from 'src/app/dialogs/bileta-confirm-dialog/models/ticket.model';
import { ConfirmDialogComponent } from 'src/app/dialogs/confirm-dialog/confirm-dialog.component';
import { Municipality } from 'src/app/home-page/models/municipality.model';
import { TicketService } from 'src/app/home-page/ticket.service';

@Component({
  selector: 'app-bileta-dashboard',
  templateUrl: './bileta-dashboard.component.html',
  styleUrls: ['./bileta-dashboard.component.scss'],
})
export class BiletaDashboardComponent implements OnInit {
  municipalities: Municipality[] = [];
  user: User;
  tickets: Ticket[] = [];

  constructor(
    public dialog: MatDialog,
    private ticketService: TicketService,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.municipalities = this.ticketService.getMunicipalities();
    this.authService.user.subscribe((user) => {
      this.user = user;
      this.ticketService
        .getTicketsByUserEmail(this.user.email)
        .subscribe((tickets) => {
          this.tickets = tickets;
        });
    });
  }

  dataSot: number = Date.now();

  getMunicipalityById(id: number) {
    return { ...this.municipalities.find((m) => m.id === id) };
  }

  openDialog(ticket: Ticket): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '480px',
      data: ticket,
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.ticketService
        .getTicketsByUserEmail(this.user.email)
        .subscribe((tickets) => {
          this.tickets = tickets;
        });
      console.log('The dialog was closed', result);
    });
  }
}
