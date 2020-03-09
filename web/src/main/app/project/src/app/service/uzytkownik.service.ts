import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {PacjentDetailViewDto, PacjentRejestracjaDto, ZalogowanoDto, ZalogujDto} from "@przychodnia/model/backend-model";
import {Router} from "@angular/router";
import {BehaviorSubject, Observable} from "rxjs";
import {NotificationService} from "@przychodnia/service/notification/notification.service";
import {map} from "rxjs/operators";

@Injectable()
export class UzytkownikService {

    private readonly USER: string = "user";
    private readonly TOKEN: string = "token";

    private _user$ = new BehaviorSubject<PacjentDetailViewDto | null>(null);

    constructor(private router: Router, private httpClient: HttpClient, private notificationService: NotificationService) {
        this._user$.next(JSON.parse(localStorage.getItem(this.USER)));
    }

    public logIn(zalogujDto: ZalogujDto): Observable<boolean> {
        return this.httpClient.post('/uzytkownicy/zaloguj', zalogujDto).pipe(
            map((zalogowanoResponse: ZalogowanoDto) => {
                localStorage.setItem(this.USER, JSON.stringify(zalogowanoResponse.uzytkownik));
                localStorage.setItem(this.TOKEN, zalogowanoResponse.token);
                this._user$.next(zalogowanoResponse.uzytkownik);
                this.notificationService.showSuccess('Zostałeś zalogowany.');
                return true;
            })
        );
    }

    public registerIn(pacjentRejestracjaDto: PacjentRejestracjaDto): Observable<PacjentDetailViewDto> {
        return <Observable<PacjentDetailViewDto>>this.httpClient.post('/pacjenci', pacjentRejestracjaDto);
    }

    public logOut(): void {
        localStorage.clear();
        this._user$.next(null);
    }

    get user$(): BehaviorSubject<PacjentDetailViewDto | null> {
        return this._user$;
    }

    get user(): PacjentDetailViewDto {
        return this._user$.getValue();
    }

    get loggedIn(): boolean {
        return this.user != null;
    }

    public getToken(): string {
        return localStorage.getItem(this.TOKEN);
    }

}
