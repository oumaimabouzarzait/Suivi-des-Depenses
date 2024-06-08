import { Person } from "./Person";

export class User{
    id:Number | String;
    email:String;
    password:String|null;
    role:String;
    person : Person;

    constructor(id:Number | String, email:String,password:String|null,role:String,person : Person){
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.person = person;
    }

}