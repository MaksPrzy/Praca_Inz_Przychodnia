import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";

@Injectable()
export class AuthRequestInterceptor implements HttpInterceptor {

    constructor(private uzytkownikService: UzytkownikService) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token: string = this.uzytkownikService.getToken() || '';

        request = request.clone({
            headers: request.headers.set('X-Auth-Token', token)
        });

        return next.handle(request);
    }

}
