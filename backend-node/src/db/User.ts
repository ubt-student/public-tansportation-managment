import { error } from "console"
import { resolve } from "path"
import { HTTP_CODE } from "../enums/http-status-codes"
import { DbService } from "../services/dbService"
import { PasswordService } from "../services/passwordService"
import { TokenService } from "../services/tokenService"

const { Client } = require('pg')
export class UserRepo {
    getUser = async () => {
        const client = await DbService.getPostgreConnection()
        const res = await client.query('select * from users')
        console.log(res.rows[1])
        DbService.endConnection(client)
        return res.rows
    }

    private getPassword = (email: string): Promise<string> => new Promise(async (resolve, reject) => {
        try {
            const dbClient = await DbService.getPostgreConnection();
            const res = await dbClient.query(`select password from users where email = '${email}'`);
            DbService.endConnection(dbClient);
            console.log('passwordi i hashun', res.rows[0]);
            
            resolve(res.rows[0].password)
        } catch (error) {
            reject(error)
        }
    })
    

    login = (req: any, res: any): Promise<string> => new Promise(async (resolve, reject) => {
        req.on("data", async (data: any) => {
            const loginData: any = JSON.parse(data);
            console.log('data', loginData);
            
            const tokenService = new TokenService();
            const hashedPassword = await this.getPassword(loginData.email);
            const passwordService = new PasswordService();
            passwordService.isCorrect(loginData.password, hashedPassword).then(() => {
                const token = tokenService.generateLoginToken(loginData.email);
                tokenService.verifyToken(token).then((decodedToken) => {
                  res.writeHead(HTTP_CODE.OK);
                  res.write(
                    JSON.stringify({
                      token: token,
                      exp: decodedToken.exp,
                    })
                  );
                  res.end();
                });  
            }).catch((error) => {
                res.writeHead(HTTP_CODE.Forbidden);
                res.write(JSON.stringify({ message: error }));
                res.end();
            });
        })
    })

    saveUser = async (user): Promise<string> => {
        return new Promise(async (resolve, reject) => {
            console.log(user);

            const client = await DbService.getPostgreConnection();
            const pwdService = new PasswordService();
            const saltedPassword = pwdService.hashPassword(user.password);
            const insert = await client.query(`INSERT INTO users (id, email, first_name, last_name, password, status, role)
            VALUES (nextval('users_sequence'), '${user.email}', '${user.firstName}', '${user.lastName}', '${saltedPassword}', '${user.status}', '${user.role}')`)
            DbService.endConnection(client)
            console.log('insert', insert);

            if (insert) {
                resolve('inserted me sukses')
            } else {
                reject('dishka ja huqi')
            }
        })
    }
    getUserByEmail = async (email: string) => {
        return new Promise(async(resolve, reject) => {
            const dbClient = await DbService.getPostgreConnection();
            const res = await dbClient.query(`select * from users where email = '${email}'`);
            resolve(res.rows[0]);
        })
    }
}