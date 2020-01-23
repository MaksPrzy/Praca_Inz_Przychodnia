import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {PacjentDetailViewDto, PacjentRejestracjaDto, ZalogowanoDto, ZalogujDto} from "@przychodnia/model/backend-model";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {Location} from '@angular/common';
import {map} from "rxjs/operators";

@Injectable()
export class UzytkownikService {

    private readonly UZYTKOWNIK: string = "uzytkownik";
    private readonly TOKEN: string = "token";

    constructor(private router: Router, private httpClient: HttpClient, private location: Location) {
    }

    public logIn(zalogujDto: ZalogujDto): Observable<boolean> {
        return this.httpClient.post('/uzytkownicy/zaloguj', zalogujDto)
            .pipe(
                map((zalogowanoDto: ZalogowanoDto) => {
                    localStorage.setItem(this.UZYTKOWNIK, JSON.stringify(zalogowanoDto.uzytkownik));
                    localStorage.setItem(this.TOKEN, zalogowanoDto.token);
                    return true;
                }),
            );
    }

    public registerIn(pacjentRejestracjaDto: PacjentRejestracjaDto): Observable<PacjentDetailViewDto> {
        return <Observable<PacjentDetailViewDto>>this.httpClient.post('/pacjenci', pacjentRejestracjaDto);
    }

    public logOut(): void {
        localStorage.clear();
    }

    public getUzytkownik(): PacjentDetailViewDto {
        const value: string = localStorage.getItem(this.UZYTKOWNIK);

        if (value !== null) {
            return JSON.parse(value);
        }
    }

    public getToken(): string {
        return localStorage.getItem(this.TOKEN);
    }

    public isLoggedIn(): boolean {
        return this.getToken().length > 0;
    }

}
