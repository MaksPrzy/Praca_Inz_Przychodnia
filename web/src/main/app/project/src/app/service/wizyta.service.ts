import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {
    HarmonogramZaplanowanaWizytaDto, HarmonogramZaplanowanaWizytaRequestDto,
    PacjentDetailViewDto,
    WizytaViewDto,
    ZaplanujWizyteDto
} from "@przychodnia/model/backend-model";
import {Observable} from "rxjs";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";

@Injectable()
export class WizytaService {

    constructor(private httpClient: HttpClient,
                private uzytkownikService: UzytkownikService) {
    }

    public getWizyta(id: number): Observable<WizytaViewDto> {
        const uzytkownik: PacjentDetailViewDto = this.uzytkownikService.user;
        return <Observable<WizytaViewDto>> this.httpClient.get(`/pacjenci/${uzytkownik.id}/wizyty/${id}`);
    }

    public getWizytaList(pacjentId: number): Observable<Array<WizytaViewDto>> {
        return <Observable<Array<WizytaViewDto>>>this.httpClient.get("/pacjenci/" + pacjentId + "/wizyty");
    }

    public zaplanuj(zaplanujWizyteDto: ZaplanujWizyteDto): Observable<WizytaViewDto> {
        const uzytkownik: PacjentDetailViewDto = this.uzytkownikService.user;
        return <Observable<WizytaViewDto>>this.httpClient.post(`/pacjenci/${uzytkownik.id}/wizyty`, zaplanujWizyteDto);
    }

    public getZaplanowanaWizytaNaTydzienList(dateFrom: Date, dateTo: Date, lekarzId: number, specjalizacjaId: number): Observable<Array<HarmonogramZaplanowanaWizytaDto>> {
        const requestDto: HarmonogramZaplanowanaWizytaRequestDto = <HarmonogramZaplanowanaWizytaRequestDto>{
            dateFrom: dateFrom,
            dateTo: dateTo,
            lekarzId: lekarzId,
            specjalizacjaId: specjalizacjaId
        };

        return <Observable<Array<HarmonogramZaplanowanaWizytaDto>>>this.httpClient.post(`/planowanie`, requestDto);
    }

}
