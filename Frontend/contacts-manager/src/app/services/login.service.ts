import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loggedIn = false;

  login(username: string, password: string): boolean {
    // You should implement your authentication logic here.
    // For simplicity, we'll just check if the username and password are both 'admin'.
    if (username === 'yassir' && password === 'zaml') {
      this.loggedIn = true;
      return true;
    } else {
      this.loggedIn = false;
      return false;
    }
  }

  isLoggedIn(): boolean {
    return this.loggedIn;
  }

  logout() {
    this.loggedIn = false;
  }
}
