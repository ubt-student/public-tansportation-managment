
import * as bcrypt from "bcrypt";
export class PasswordService {
  isCorrect = (password: string, hashed: string): Promise<any> => {
    return new Promise((resolve, reject) => {
      bcrypt.compare(password, hashed, function (err, result) {
        if (result) {
          resolve(result);
        } else {
          reject("incorrect username or password");
        }
      });
    });
  }

  hashPassword = (password) => {
    const salt = bcrypt.genSaltSync(10);
    return bcrypt.hashSync(password, salt);
  }
}