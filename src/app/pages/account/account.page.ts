import { Component } from '@angular/core';

@Component({
  selector: 'app-account',
  templateUrl: 'account.page.html',
  styleUrls: ['account.page.scss'],
  standalone: false,
})
export class AccountPage {

  email: string = '';
  password: string = '';

  isLoggedIn: boolean = false;

  constructor() { }

  login(): void {

  }

  register(): void {

  }

  logout(): void {

  }

}
