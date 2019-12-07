import {Component, OnInit} from "@angular/core";
import {LekarzService} from "@przychodnia/service/lekarz.service";
import {HarmonogramViewDto} from "@przychodnia/model/backend-model";

@Component({
    selector: 'mp-wizyta-planowanie',
    templateUrl: './wizyta-planowanie.component.html',
    styleUrls: ['./wizyta-planowanie.component.scss']
})
export class WizytaPlanowanieComponent implements OnInit {

    harmonogram: HarmonogramViewDto;

    constructor(private lekarzService: LekarzService) {
    }

    ngOnInit(): void {
        console.log('1');
        let harmonogramCollection: Array<HarmonogramViewDto>;

        this.lekarzService.getHarmonogramList(2, 4)
            .subscribe((harmonogramCollectionResponse: Array<HarmonogramViewDto>) => {
                console.log('2');
                console.dir(harmonogramCollectionResponse);
                console.log('3');
                this.harmonogram = harmonogramCollectionResponse.pop();
            });

        console.dir(this.harmonogram);
    }

}
