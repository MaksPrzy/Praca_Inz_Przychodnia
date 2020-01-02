import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {PacjentDetailViewDto, PacjentNewDto} from "@przychodnia/model/backend-model";
import {Router} from "@angular/router";

@Injectable()
export class UzytkownikRejestracjaService {

    private readonly UZYTKOWNIK: string = "uzytkownik";
    private readonly TOKEN: string = "token";

    constructor(private router: Router, private httpClient: HttpClient) {
    }

    // public registerIn(: ZalogujDto): void {
    //     this.httpClient.post('/rejestracja', )
    //         .subscribe((: ZalogowanoDto) => {
    //             localStorage.setItem(this.UZYTKOWNIK, JSON.stringify(zalogowanoDto.uzytkownik));
    //             localStorage.setItem(this.TOKEN, zalogowanoDto.token);
    //
    //             this.router.navigate(['/home']);
    //         });
    // }

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
