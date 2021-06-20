export class Geo {
  constructor(public type: string, public features: Features[]) {}
}

class Features {
  constructor(
    public type: string,
    public id: string,
    public geometry: Geometry,
    public properties?: string,
    public diff?: number,
    public diffvaluelevelpercentage?: number
  ) {}
}

class Geometry {
  constructor(public type: string, public coordinates: number[]) {}
}
