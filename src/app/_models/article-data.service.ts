import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Article } from './Article';

@Injectable({
    providedIn: 'root'
  })
export class articleDataService {

    private myArticle = new Subject<Article>();
    myArticleObservable = this.myArticle.asObservable();

    addArticle(atricle: Article){
        this.myArticle.next(atricle);
    }
}