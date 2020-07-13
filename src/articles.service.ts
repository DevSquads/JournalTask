import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {
  onSubmit(title: any, description: any, id: any) {
    const obj = {
      title,
      description,
      
    };
    console.log(obj);
    this.http.post(`${this.uri}/create`, obj)
        .subscribe(res => console.log('Done'));
  }


  uri = 'http://localhost:4000/articles';

  constructor(private http: HttpClient) { }

  addArticle(title, description) {
    const obj = {
      title,
      description,
      
    };
    console.log(obj);
    this.http.post(`${this.uri}/create`, obj)
        .subscribe(res => console.log('Done'));
  }

  getArticles() {
    return this
           .http
           .get(`${this.uri}`);
  }
  deleteArticle(id) {
    return this
              .http
              .get(`${this.uri}/delete/${id}`);
  }
  editArticle(id) {
    return this
            .http
            .get(`${this.uri}/edit/${id}`);
    }

    updateArticle(title, description, id) {
      const obj = {
        title,
        description,
    
      };
      this
        .http
        .post(`${this.uri}/aricles/${id}`, obj)
        .subscribe(res => console.log('Done'));
  }
}