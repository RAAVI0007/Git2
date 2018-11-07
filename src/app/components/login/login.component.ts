import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AlertService } from '../../services/Alert/alert.service';
import { AuthenticationService } from '../../services/Authentication/authentication.service';
import { TokenStorage } from '../../classes/token.storage' ;

@Component({templateUrl: 'login.component.html'})
@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
  })

export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private token: TokenStorage,
        private authenticationService: AuthenticationService,
        private alertService: AlertService) {}

        username: string;
        password: string;

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        // reset login status
        this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    login() {
        console.log(this.username) ;
        console.log(this.password);
    this.authenticationService.attemptAuth(this.username, this.password).subscribe(
        data => {
          this.token.saveToken(data.token);
          console.log('data.token>>' + data.token );
          this.router.navigate(['user']);
        }
      );
    }
}
