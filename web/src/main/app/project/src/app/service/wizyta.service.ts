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

    public getWizytaList(pacjentId: number): Observable<Array<WizytaViewDto>> {
        return <Observable<Array<WizytaViewDto>>>this.httpClient.get("/pacjenci/" + pacjentId + "/wizyty");
    }

    public zaplanuj(zaplanujWizyteDto: ZaplanujWizyteDto): Observable<WizytaViewDto> {
        const uzytkownik: PacjentDetailViewDto = this.uzytkownikService.getUzytkownik();
        return <Observable<WizytaViewDto>>this.httpClient.post(`/pacjenci/${uzytkownik.id}/wizyty`, zaplanujWizyteDto);
    }

    // public getZaplanowanaWizytaNaTydzienList():

}
