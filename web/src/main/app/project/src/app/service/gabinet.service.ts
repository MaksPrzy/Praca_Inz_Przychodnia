import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {GabinetEditDto, GabinetNewDto, GabinetViewDto} from "@przychodnia/model/backend-model";

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

    public update(gabinetViewDto: GabinetEditDto): Observable<GabinetViewDto> {
        return <Observable<GabinetViewDto>>this.httpClient.post('/gabinety', gabinetViewDto);
    }l

}
