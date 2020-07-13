import { User } from "./user";

export class Article {
    title: string;
    description: string;
    user: User;

    constructor(  title: string, description: string) {
        this.title = title;
        this.description = description; 
      }
   
}

