import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {WizytaService} from "@przychodnia/service/wizyta.service";
import {WizytaViewDto} from "@przychodnia/model/backend-model";
import {switchMap} from "rxjs/operators";
import {Observable} from "rxjs";

@Component({
    selector: 'mp-wizyta-podsumowanie',
    templateUrl: './wizyta-podsumowanie.component.html',
    styleUrls: ['./wizyta-podsumowanie.component.scss']
})
export class WizytaPodsumowanieComponent implements OnInit {

    wizyta$: Observable<WizytaViewDto>;

    constructor(private activatedRoute: ActivatedRoute,
                private wizytaService: WizytaService) {

    }

    ngOnInit(): void {
        this.activatedRoute.paramMap
            .subscribe((params: ParamMap) => {
                console.log('podsumowanie');
                console.dir(params);
                const wizytaId: number = parseInt(params.get('id'));

                this.wizyta$ = this.wizytaService.getWizyta(wizytaId);
            });
    }

}
