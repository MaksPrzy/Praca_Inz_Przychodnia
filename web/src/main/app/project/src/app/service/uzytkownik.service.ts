import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {PacjentDetailViewDto, PacjentNewDto, ZalogowanoDto, ZalogujDto} from "@przychodnia/model/backend-model";
import {Router} from "@angular/router";

@Injectable()
export class UzytkownikService {

    private readonly UZYTKOWNIK: string = "uzytkownik";
    private readonly TOKEN: string = "token";

    constructor(private router: Router, private httpClient: HttpClient) {
    }

    public logIn(zalogujDto: ZalogujDto): void {
        this.httpClient.post('/uzytkownicy/zaloguj', zalogujDto)
            .subscribe((zalogowanoDto: ZalogowanoDto) => {
                localStorage.setItem(this.UZYTKOWNIK, JSON.stringify(zalogowanoDto.uzytkownik));
                localStorage.setItem(this.TOKEN, zalogowanoDto.token);

                this.router.navigate(['/home']);
            });
    }

    public register(newUzytkownik: PacjentNewDto) {

    }

    public logOut(): void {
        localStorage.clear();
    }

    public getUzytkownik(): PacjentDetailViewDto {
        return JSON.parse(localStorage.getItem(this.UZYTKOWNIK));
    }

    public getToken(): string {
        return localStorage.getItem(this.TOKEN);
    }

    public isLoggedIn(): boolean {
        return this.getToken().length > 0;
    }

}
