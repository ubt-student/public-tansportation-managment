const { Client } = require('pg')

export class DbService {
    static getPostgreConnection = async () =>{
        const client = new Client({
            host: 'localhost',
            port: 5432,
            user: 'postgres',
            password: 'toka156324',
            database: 'ptm'
        })
        await client.connect()
        return client
    }
    static endConnection(connection){
        connection.end();
    }
}