import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

// used to create fake backend
import { fakeBackendProvider } from './_helpers';

import { AppComponent } from './app.component';
import { appRoutingModule } from './app.routing';

import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { HomeComponent } from './home';
import { AdminComponent } from './admin';
import { LoginComponent } from './login';;
import { ArticleAddComponent } from './article-add/article-add.component'
import { SlimLoadingBarModule } from 'ng2-slim-loading-bar';
import { ArticlesService } from 'articles.service';;
import { ArticleEditComponent } from './article-edit/article-edit.component'
import { articleDataService } from './_models/article-data.service';


@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        HttpClientModule,
        appRoutingModule,
        SlimLoadingBarModule,
        FormsModule,
        


    ],
    declarations: [
        AppComponent,
        HomeComponent,
        AdminComponent,
        LoginComponent,
        ArticleAddComponent,
        ArticleEditComponent,
    ],
    providers: [
        
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

        // provider used to create fake backend
        fakeBackendProvider,
        articleDataService

    
    ],
   
    bootstrap: [AppComponent],
  
})

export class AppModule { }