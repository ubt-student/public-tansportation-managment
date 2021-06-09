import { HTTP_CODE } from "../enums/http-status-codes"
import { DbService } from "../services/dbService"


const { Client } = require('pg')
export class TicketsRepo {
    getTickets = async () => {
        const client = await DbService.getPostgreConnection()
        const res = await client.query('select * from tickets')
        console.log(res.rows[1])
        DbService.endConnection(client)
        return res.rows
    }

    saveTicket = async (ticket): Promise<string> => {
        return new Promise(async (resolve, reject) => {
            console.log(ticket);

            const client = await DbService.getPostgreConnection();
            const insert = await client.query(`INSERT INTO tickets (ticket_id, company, user_id, booking_time, linja_id)
            VALUES (nextval('tickets_sequence'),'${ ticket.company }','${ ticket.user_id }','${ ticket.booking_time }','${ ticket.linja_id }')`);
            DbService.endConnection(client)
            console.log('insert', insert);

            if (insert) {
                resolve('inserted me sukses')
            } else {
                reject('dishka ja huqi')
            }
        })
    }
}