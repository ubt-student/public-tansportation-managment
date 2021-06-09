import * as http from "http";
import * as domain from "domain";
import { get as getConfig } from "config";
import { ReqUrlMiddleware } from "./http-middleware/req-url-middleware";

export class Server {
  start = (): Promise<any> => {
    const serverDomain = domain.create();
    return new Promise((resolve) => {
      serverDomain.run(() => {
        if (getConfig("app_secret") !== "") {
          http
            .createServer((req: any, res: any) => {
              const reqd = domain.create();
              reqd.add(req);
              reqd.add(res);

              reqd.on("error", (er) => {
                console.error("Error", er, req.url);
                try {
                  res.writeHead(500);
                  res.end("Error occurred: " + er);
                } catch (er2) {
                  console.error("Error sending 500", er2, req.url);
                }
              });
              res.setHeader("Content-Type", "application/json");
              res.setHeader('Access-Control-Allow-Origin', '*');
              res.setHeader('Access-Control-Allow-Methods', '*');
              res.setHeader('Access-Control-Max-Age', 2592000); // 30 days
              const headers = {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'OPTIONS, POST, GET',
                'Access-Control-Max-Age': 2592000, // 30 days
                'Access-Control-Allow-Headers': '*'
                /** add other headers as per requirement */
              };

              if (req.method === 'OPTIONS') {
                res.writeHead(204, headers);
                res.end();
              } else {
                const reqUrl = new URL(req.url, "http://127.0.0.1/");
                const reqUrlMiddleware = new ReqUrlMiddleware();
                reqUrlMiddleware.handleRequest(req, res, reqUrl);
              }
            })
            .listen(getConfig("port"), () => {
              resolve(
                `Server is running at http://127.0.0.1:${getConfig("port")}`
              );
            });
        } else {
          console.log("app_secret missing in config");
          throw new Error("app_secret missing in config");
        }
      });
    });
  };
}