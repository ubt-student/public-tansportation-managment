import { HttpRequestHandlers } from "./request-handlers";

export class Router {
  httpRequestHandlers: HttpRequestHandlers = new HttpRequestHandlers();
  public routes: any = {
    // "GET/user/:id": {
    //   controller: this.httpRequestHandlers.getuser,
    //   authorized: false,
    // },
    // "GET/most-liked": {
    //   controller: this.httpRequestHandlers.getMostLikedUsers,
    //   authorized: false,
    // },
    // "PUT/user": {
    //   controller: this.httpRequestHandlers.updateUser,
    //   authorized: true,
    // },
    "POST/signup": {
      controller: this.httpRequestHandlers.signup,
      authorized: false,
    },
    "POST/login": {
      controller: this.httpRequestHandlers.login,
      authorized: false,
    },
    "GET/user": {
      controller: this.httpRequestHandlers.getLogedUser,
      authorized: true,
    },
     "POST/bileta": {
       controller: this.httpRequestHandlers.noResponse,
       authorized: false,
     },
    "POST/tickets": {
      controller: this.httpRequestHandlers.tickets,
      authorized: false,
    },
    "GET/tickets": {
      controller: this.httpRequestHandlers.getTickets,
      authorized: false,
    },
    "POST/lines": {
      controller: this.httpRequestHandlers.lines,
      authorized: false,
    },
    "GET/lines": {
      controller: this.httpRequestHandlers.getLines,
      authorized: false,
    },
    // "POST/user/:id/unlike": {
    //   controller: this.httpRequestHandlers.unlikeUser,
    //   authorized: true,
    // },
    "GET/data": {
        controller: this.httpRequestHandlers.data,
        authorized: false
    },
    default: {
      controller: this.httpRequestHandlers.noResponse,
      authorized: false,
    },
  };
}