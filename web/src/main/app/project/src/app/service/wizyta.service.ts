import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {PacjentDetailViewDto, WizytaViewDto, ZaplanujWizyteDto} from "@przychodnia/model/backend-model";
import {Observable} from "rxjs";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";

@Injectable()
export class WizytaService {

    private readonly UZYTKOWNIK: string = "uzytkownik";
    private readonly TOKEN: string = "token";

    constructor(private httpClient: HttpClient,
                private uzytkownikService: UzytkownikService) {
    }

    public getWizyta(id: number): Observable<WizytaViewDto> {
        const uzytkownik: PacjentDetailViewDto = this.uzytkownikService.getUzytkownik();
        return <Observable<WizytaViewDto>> this.httpClient.get(`/pacjenci/${uzytkownik.id}/wizyty/${id}`);
    }

    public getWszystkieWizyty(pacjentid: number): Observable<any> {
        return this.httpClient.get<any>("/pacjenci/" + pacjentid + "/wizyty");
    }

//     return this.http.post<CreatePost>(environment.apiEndpoint + "posts", post,
// { headers: {'Authorization': 'Bearer ' + this.oauth.getToken(), 'Content-Type':  'application/json'}});
//

    public zaplanuj(zaplanujWizyteDto: ZaplanujWizyteDto): Observable<WizytaViewDto> {
        const uzytkownik: PacjentDetailViewDto = this.uzytkownikService.getUzytkownik();
        return <Observable<WizytaViewDto>>this.httpClient.post(`/pacjenci/${uzytkownik.id}/wizyty`, zaplanujWizyteDto);
    }

}
