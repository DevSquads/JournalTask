import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from 'app/_models';
import { UserService } from 'app/_services';

import { ArticlesService } from 'articles.service';
import { Article } from 'app/_models/Article';

@Component({ templateUrl: 'admin.component.html' })
export class AdminComponent implements OnInit {
    loading = false;
    articles: Article[];
  constructor(private ps: ArticlesService) { }

  ngOnInit() {
    this.ps
      .getArticles()
      .subscribe((data: Article[]) => {
        this.articles = data;
    });
  }
  deleteArticle(id) {
    this.ps.deleteArticle(id).subscribe(res => {
      this.articles.splice(id, 1);
    });
  }
  


}
