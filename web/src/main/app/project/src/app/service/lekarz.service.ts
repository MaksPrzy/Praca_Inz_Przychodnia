import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HarmonogramViewDto, LekarzDetailViewDto} from "@przychodnia/model/backend-model";
import {filter} from "rxjs/operators";

@Injectable()
export class LekarzService {

    constructor(private httpClient: HttpClient) {
    }

    public getLekarzList(searchBy: string): Observable<Array<LekarzDetailViewDto>> {
        return <Observable<Array<LekarzDetailViewDto>>>this.httpClient.get('/lekarze?searchBy=' + searchBy);
    }

    public getHarmonogram(lekarzId: number, specjalizacjaId: number): Observable<HarmonogramViewDto> {
        let result: HarmonogramViewDto;

        this.httpClient.get(`/lekarze/${lekarzId}/harmonogramy`)
            .pipe(
                filter((harmonogram: HarmonogramViewDto) => harmonogram.specjalizacjaId == specjalizacjaId)
            )
            .subscribe((harmonogram: HarmonogramViewDto) => {
                result = harmonogram;
            });

        return Observable.create(result);
    }

}
