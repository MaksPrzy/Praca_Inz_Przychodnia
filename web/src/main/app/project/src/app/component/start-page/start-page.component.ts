import {Component} from "@angular/core";
import {LekarzService} from "@przychodnia/service/lekarz.service";
import {LekarzDetailViewDto} from "@przychodnia/model/backend-model";


@Component({
    selector: 'mp-start',
    templateUrl: './start-page.component.html',
    styleUrls: ['./start-page.component.scss']
})
export class StartPageComponent {

    searchBy: string = '';
    lekarzCollection: Array<LekarzDetailViewDto>;

    constructor(private lekarzService: LekarzService) {

    }

    public onSearch(): void {
        this.lekarzService.getLekarzList(this.searchBy).subscribe((lekarzListResponse: Array<LekarzDetailViewDto>) => {
            this.lekarzCollection = lekarzListResponse;
        });
    }

    public onClear(): void {
        this.searchBy = '';
    }

}
