import {AfterViewInit, Component} from "@angular/core";
import {LekarzService} from "@przychodnia/service/lekarz.service";
import {HarmonogramViewDto} from "@przychodnia/model/backend-model";

@Component({
    selector: 'mp-wizyta-planowanie',
    templateUrl: './wizyta-planowanie.component.html',
    styleUrls: ['./wizyta-planowanie.component.scss']
})
export class WizytaPlanowanieComponent implements AfterViewInit {

    harmonogram: HarmonogramViewDto;

    constructor(private lekarzService: LekarzService) {
    }

    ngAfterViewInit(): void {
        this.lekarzService.getHarmonogram(2, 4)
            .subscribe((harmonogramResponse: HarmonogramViewDto) => {
                this.harmonogram = harmonogramResponse;
            });
        console.dir(this.harmonogram);
    }

}
