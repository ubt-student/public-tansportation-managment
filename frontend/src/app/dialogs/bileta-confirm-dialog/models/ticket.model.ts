import { Travel } from 'src/app/home-page/models/travel.model';

export class Ticket {
  id: number;
  userEmail: string;
  amount: number;
  travel: Travel;
}
