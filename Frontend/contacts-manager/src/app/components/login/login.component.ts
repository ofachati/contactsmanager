import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Import the Router
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

  login() {
    if (this.loginService.login(this.username, this.password)) {
      // Redirect to the "Home" component on successful login
      this.router.navigate(['/home']);
    } else {
      // Handle login failure (e.g., show an error message).
      console.log('Login failed');
    }
  }
}
