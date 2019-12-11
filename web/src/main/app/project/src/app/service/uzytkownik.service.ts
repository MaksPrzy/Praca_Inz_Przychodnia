import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {PacjentDetailViewDto, ZalogowanoDto, ZalogujDto} from "@przychodnia/model/backend-model";

@Injectable()
export class UzytkownikService {

    public uzytkownik: PacjentDetailViewDto;
    public token: string;
    public isLoggedIn: boolean;

    constructor(private httpClient: HttpClient) {
        this.isLoggedIn = false;
    }

    public logIn(): void {
        const zalogujDto: ZalogujDto = {
            username: 'JAKISLOGIN',
            password: 'haslo123'
        };

        this.httpClient.post('/uzytkownik/zaloguj', zalogujDto)
            .subscribe((zalogowanoDto: ZalogowanoDto) => {
                this.uzytkownik = zalogowanoDto.uzytkownik;
                this.token = zalogowanoDto.token;
                this.isLoggedIn = true;
            });
    }

    public logOut(): void {
        this.uzytkownik = null;
        this.token = null;
        this.isLoggedIn = false;
    }

}
