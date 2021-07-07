import { Route } from './route.model';

export class Travel {
  id: number;
  agency: string;
  departTime: string;
  arrivalTime: string;
  price: number;
  route: Route;
}
