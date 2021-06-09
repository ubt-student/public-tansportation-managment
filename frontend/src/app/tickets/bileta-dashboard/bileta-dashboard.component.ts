import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/dialogs/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-bileta-dashboard',
  templateUrl: './bileta-dashboard.component.html',
  styleUrls: ['./bileta-dashboard.component.scss']
})
export class BiletaDashboardComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '480px',
      data: { message: 'A jeni te sigurt qe deshironi te anuloni rezervimin e biletes suaj?' }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      
    });
  }
}
