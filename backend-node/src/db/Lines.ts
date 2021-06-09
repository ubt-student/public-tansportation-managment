import { HTTP_CODE } from "../enums/http-status-codes"
import { DbService } from "../services/dbService"


const { Client } = require('pg')
export class LinesRepo {
    getLines = async () => {
        const client = await DbService.getPostgreConnection()
        const res = await client.query('select * from linja')
        console.log(res.rows[1])
        DbService.endConnection(client)
        return res.rows
    }

    saveLines = async (line): Promise<string> => {
        return new Promise(async (resolve, reject) => {
            console.log(line);

            const client = await DbService.getPostgreConnection();
            const insert = await client.query(`INSERT INTO linja (linja_id, company, vendnisja, destinacioni, nisja, arritja)
            VALUES (nextval('linja_sequence'),'${ line.company }','${ line.vendnisja }','${ line.destinacioni }','${ line.nisja }','${ line.arritja }')`);
            DbService.endConnection(client)
            console.log('insert', insert);

            if (insert) {
                resolve('inserted me sukses')
            } else {
                reject('diqka ja huqi')
            }
        })
    }
}