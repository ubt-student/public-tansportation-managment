export class User {
  constructor(
    public email: string,
    public firstname: string,
    public lastname: string,
    public avatar: string,
    public jwt: string,
    public expirationDate: Date,
    public admin: boolean
  ) {}
}
