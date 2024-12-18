import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable, Subject} from 'rxjs';
import {LoginService} from "../services/login.service";
import {JwtAuthenticationResponse} from "../model/Login";

@Injectable({
  providedIn: 'root'
})
export class SecurityGuard implements CanActivate {

  private isTokenExpiredSubject: Subject<boolean> = new Subject<boolean>();
  public isTokenExpired$: Observable<boolean> = this.isTokenExpiredSubject.asObservable();


  constructor(private loginService: LoginService, private router: Router) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    this.loginService.isTokenExpired().subscribe(
      (result: JwtAuthenticationResponse) => {
        localStorage.setItem('token', 'Bearer ' + result.token);
        this.isTokenExpiredSubject.next(true);
      },
      (error) => {
        this.router.navigate(['/login']);
      }
    );
    return this.isTokenExpired$;
  }

}
