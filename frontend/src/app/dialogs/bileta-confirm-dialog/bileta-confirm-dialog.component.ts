import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-bileta-confirm-dialog',
  templateUrl: './bileta-confirm-dialog.component.html',
  styleUrls: ['./bileta-confirm-dialog.component.scss']
})
export class BiletaConfirmDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<BiletaConfirmDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
