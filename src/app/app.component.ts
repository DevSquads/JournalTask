import { Component } from '@angular/core';
import { Router ,NavigationCancel,
    Event,
    NavigationEnd,
    NavigationError,
    NavigationStart} from '@angular/router';
import {SlimLoadingBarService} from 'ng2-slim-loading-bar';

import { AuthenticationService } from './_services';
import { User, Role } from './_models';

@Component({ selector: 'app', templateUrl: 'app.component.html', styleUrls: ['./app.component.css'] })
export class AppComponent {
    title = 'Legendary';

    currentUser: User;

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService,
        private loadingBar: SlimLoadingBarService, 
    ) {
        this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
        this.router.events.subscribe((event: Event) => {
            this.navigationInterceptor(event);
          });
    }

    get isAdmin() {
        return this.currentUser && this.currentUser.role === Role.Admin;
    }

    logout() {
        this.authenticationService.logout();
        this.router.navigate(['/login']);
    }
    private navigationInterceptor(event: Event): void {
        if (event instanceof NavigationStart) {
          this.loadingBar.start();
        }
        if (event instanceof NavigationEnd) {
          this.loadingBar.complete();
        }
        if (event instanceof NavigationCancel) {
          this.loadingBar.stop();
        }
        if (event instanceof NavigationError) {
          this.loadingBar.stop();
        }
      }
}