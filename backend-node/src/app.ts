import { Server } from "./server";


const server = new Server();
server.start().then((serverStatus) => {
    console.log(`${serverStatus}\n`);
});