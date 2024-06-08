import { Person } from "./Person";

export class UserDto{
    email:String;
    password:String;
    person:Person | null;
    role:String | null;

    constructor(email:String, password:String, person:Person | null,role:String | null){
        this.email = email;
        this.password = password;
        this.person = person;
        this.role= role;
    }
}