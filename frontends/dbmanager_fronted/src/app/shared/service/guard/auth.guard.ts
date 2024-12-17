import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import {Injectable} from "@angular/core";
import {Observable, Subject} from "rxjs";
import {LoginService} from "../http/login/login.service";


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  private isTokenExpiredSubject: Subject<boolean> = new Subject<boolean>();
  public isTokenExpired$: Observable<boolean> = this.isTokenExpiredSubject.asObservable();


  constructor(private loginService: LoginService, private router: Router) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    return true;
  }

}
