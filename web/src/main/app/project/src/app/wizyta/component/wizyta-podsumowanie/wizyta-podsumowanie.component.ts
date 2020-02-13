import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {WizytaService} from "@przychodnia/service/wizyta.service";
import {WizytaViewDto} from "@przychodnia/model/backend-model";

@Component({
    selector: 'mp-wizyta-podsumowanie',
    templateUrl: './wizyta-podsumowanie.component.html',
    styleUrls: ['./wizyta-podsumowanie.component.scss']
})
export class WizytaPodsumowanieComponent implements OnInit {

    wizyta: WizytaViewDto;

    constructor(private activatedRoute: ActivatedRoute,
                private wizytaService: WizytaService) {

    }

    ngOnInit(): void {
        this.activatedRoute.paramMap
            .subscribe((params: ParamMap) => {
                const wizytaId: number = parseInt(params.get('id'));

                this.wizytaService.getWizyta(wizytaId).subscribe((wizytaResponse: WizytaViewDto) => {
                    console.log(wizytaResponse);
                    this.wizyta = wizytaResponse;
                });
            });
    }

}


