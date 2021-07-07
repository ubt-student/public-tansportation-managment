import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/auth/auth.service';
import { User } from 'src/app/auth/models/user.model';
import { Municipality } from 'src/app/home-page/models/municipality.model';
import { Travel } from 'src/app/home-page/models/travel.model';
import { TicketService } from 'src/app/home-page/ticket.service';
import { Ticket } from './models/ticket.model';

@Component({
  selector: 'app-bileta-confirm-dialog',
  templateUrl: './bileta-confirm-dialog.component.html',
  styleUrls: ['./bileta-confirm-dialog.component.scss'],
})
export class BiletaConfirmDialogComponent implements OnInit {
  municipalities: Municipality[] = [];
  prices = [
    { currency: 'euro', sign: '€' },
    { currency: 'dollar', sign: '$' },
    { currency: 'franga', sign: 'CHF' },
  ];
  price = { currency: 'euro', sign: '€' };
  euroUsdRate: number = 1.2;
  euroChfRate: number = 1.05;
  user: User;
  registered = false;

  constructor(
    public dialogRef: MatDialogRef<BiletaConfirmDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public travel: Travel,
    private ticketService: TicketService,
    private authService: AuthService,
  ) {}

  ngOnInit() {
    this.municipalities = this.ticketService.getMunicipalities();
    this.authService.user.subscribe((user) => {
      this.user = user;
    });
    this.ticketService.getRates('EUR_CHF').subscribe((data: any) => {
      this.euroChfRate = data.EUR_CHF;
    });
    this.ticketService.getRates('EUR_USD').subscribe((data: any) => {
      this.euroUsdRate = data.EUR_USD;
    });
  }

  getMunicipalityById(id: number) {
    return { ...this.municipalities.find((m) => m.id === id) };
  }

  rezervo() {
    const ticket = new Ticket();
    ticket.amount = this.travel.price;
    ticket.travel = this.travel;
    ticket.userEmail = this.user.email;
    this.ticketService.registerTicket(ticket).subscribe(() => {
      this.registered = true;
    });
  }

  calculatePrice(amount: number) {
    let calculatedAmount = amount;
    if (this.price.sign === 'CHF') {
      calculatedAmount = this.euroChfRate * amount;
    }
    if (this.price.sign === '$') {
      calculatedAmount = this.euroUsdRate * amount;
    }
    return calculatedAmount.toFixed(2);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
