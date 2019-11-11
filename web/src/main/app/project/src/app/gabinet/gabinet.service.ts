// @ts-ignore
import {HttpClient} from "@angular/common/http";
// @ts-ignore
import {Observable} from "rxjs";
// @ts-ignore
import {Injectable} from "@angular/core";
import {GabinetNewDto, GabinetViewDto} from "../model/backend-model";

// @ts-ignore
@Injectable()
export class GabinetService {

    constructor(private httpClient: HttpClient) {
    }

    public getGabinetList(): Observable<Array<GabinetViewDto>> {
        return <Observable<Array<GabinetViewDto>>>this.httpClient.get('/gabinety');
    }

    public add(gabinetNewDto: GabinetNewDto): Observable<GabinetViewDto> {
        return <Observable<GabinetViewDto>>this.httpClient.post('/gabinety', gabinetNewDto);
    }

}
