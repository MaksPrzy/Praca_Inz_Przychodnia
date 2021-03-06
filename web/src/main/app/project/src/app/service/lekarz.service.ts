import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HarmonogramViewDto, LekarzDetailViewDto} from "@przychodnia/model/backend-model";

@Injectable()
export class LekarzService {

    constructor(private httpClient: HttpClient) {
    }

    public getLekarzList(searchBy: string): Observable<Array<LekarzDetailViewDto>> {
        return <Observable<Array<LekarzDetailViewDto>>>this.httpClient.get('/lekarze?searchBy=' + searchBy);
    }

    public getLekarz(id: number): Observable<LekarzDetailViewDto> {
        return <Observable<LekarzDetailViewDto>>this.httpClient.get(`/lekarze/${id}`);
    }

    public getHarmonogramList(lekarzId: number): Observable<Array<HarmonogramViewDto>> {
        return <Observable<Array<HarmonogramViewDto>>>this.httpClient.get(`/lekarze/${lekarzId}/harmonogramy`);
    }

}
