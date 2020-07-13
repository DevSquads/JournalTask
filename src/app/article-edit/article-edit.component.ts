
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticlesService } from 'articles.service';

import { NgForm } from '@angular/forms';
import { Article } from 'app/_models/Article';
import { articleDataService } from 'app/_models/article-data.service';



@Component({
  selector: 'app-article-edit',
  templateUrl: './article-edit.component.html',

})
export class ArticleEditComponent implements OnInit {

  angForm: FormGroup;
  article: any = {};


  @ViewChild('myForm', { static: false }) articleForm: NgForm;    
  constructor(private articleData: articleDataService, private route: ActivatedRoute, private router: Router, private ps: ArticlesService, private fb: FormBuilder) {
      this.createForm();
 }
 onSubmit(){
  const newArticle = new Article(this.articleForm.value['article.title'], this.articleForm.value['article.description']);
  this.articleData.addArticle(newArticle);
  this.router.navigate(['articles']);

}

  createForm() {
    this.angForm = this.fb.group({
      title: ['', Validators.required ],
      description: ['', Validators.required ]
    });
  }
 


  ngOnInit() {
    this.route.params.subscribe(params => {
        this.ps.editArticle(params['id']).subscribe(res => {
          this.article = res;
      });
    });
  }
  updateArticle(title, description, id) {
    this.route.params.subscribe(params => {
      this.ps.updateArticle(title, description, params.id);
      this.router.navigate(['articles']);
    });
  }
  
}