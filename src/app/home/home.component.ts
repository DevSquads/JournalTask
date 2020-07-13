import { Component } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from 'app/_models';
import { UserService, AuthenticationService } from 'app/_services';

import { OnInit, OnDestroy } from '@angular/core';
import { Article } from 'app/_models/Article';
import { articleDataService } from 'app/_models/article-data.service';


@Component({
    selector: 'app-article-edit',
    templateUrl: 'home.component.html'
},)

export class HomeComponent implements OnInit, OnDestroy {
    loading = false;
    currentUser: User;
    userFromApi: User;
    article:Article; 

    constructor(
        private userService: UserService,
        private authenticationService: AuthenticationService,
        private articleData: articleDataService
    ) {
        this.currentUser = this.authenticationService.currentUserValue;
    }
    

    ngOnInit() {
        this.articleData.myArticleObservable.subscribe(
            (article: Article) => {
              this.article = article;
              console.log(article);
            }
          );
        this.loading = true;
        this.userService.getById(this.currentUser.id).pipe(first()).subscribe(user => {
            this.loading = false;
            this.userFromApi = user;
        });
    }
    ngOnDestroy(){

    }
}