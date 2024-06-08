import { User } from "./User";

export class LoginResponseDto{
    token:String;
    message:String;
    user: User| null;

    constructor(token:String, message:String, user:User| null){
        this.token = token;
        this.message = message;
        this.user = user;
    }
}
