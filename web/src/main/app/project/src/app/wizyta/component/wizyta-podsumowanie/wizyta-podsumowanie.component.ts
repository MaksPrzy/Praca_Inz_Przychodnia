import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {WizytaService} from "@przychodnia/service/wizyta.service";
import {
    AbstractLekarzDto,
    HarmonogramViewDto,
    LekarzDetailViewDto,
    WizytaViewDto
} from "@przychodnia/model/backend-model";
import {Observable} from "rxjs";
import {LekarzService} from "@przychodnia/service/lekarz.service";

@Component({
    selector: 'mp-wizyta-podsumowanie',
    templateUrl: './wizyta-podsumowanie.component.html',
    styleUrls: ['./wizyta-podsumowanie.component.scss']
})
export class WizytaPodsumowanieComponent implements OnInit {

    wizyta$: Observable<WizytaViewDto>;
    harmonogram$: Observable<HarmonogramViewDto>

    constructor(private activatedRoute: ActivatedRoute,
                private wizytaService: WizytaService,
                private lekarzService: LekarzService) {

    }

    ngOnInit(): void {
        this.activatedRoute.paramMap
            .subscribe((params: ParamMap) => {
                console.log('podsumowanie');
                console.dir(params);
                const wizytaId: number = parseInt(params.get('id'));
                const lekarzId: number = parseInt(params.get('imie'));
                const lekarzSpecjalizacja: number = parseInt(params.get('specjalizacja'));

                this.wizyta$ = this.wizytaService.getWizyta(wizytaId);
                this.harmonogram$ = this.lekarzService.getHarmonogramList(lekarzId, lekarzSpecjalizacja);
            });
    }

}
