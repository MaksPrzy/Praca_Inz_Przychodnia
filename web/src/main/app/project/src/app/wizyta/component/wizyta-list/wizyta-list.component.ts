import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {WizytaService} from "@przychodnia/service/wizyta.service";
import {PacjentDetailViewDto, WizytaViewDto} from "@przychodnia/model/backend-model";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";

@Component({
    selector: 'mp-wizyta-list',
    templateUrl: './wizyta-list.component.html',
    styleUrls: ['./wizyta-list.component.scss']
})
export class WizytaListComponent implements OnInit {

    wizyta: WizytaViewDto
    pacjent: PacjentDetailViewDto
    uzytkownik: PacjentDetailViewDto


    constructor(private activatedRoute:  ActivatedRoute,
                private wizytaService: WizytaService,
                private uzytkownikService: UzytkownikService){
    }

    ngOnInit(): void {
        this.uzytkownik = this.uzytkownikService.getUzytkownik();
        console.log(this.uzytkownik);
        this.activatedRoute.paramMap
            .subscribe((params: ParamMap) => {
                this.wizytaService.getWszystkieWizyty(this.uzytkownik.id).subscribe(data => {
                    console.log(data);
                    // this.wizyta = pacjentResponse;
                });
            });
    }

    private loadWizytaCollection(): void {
    }

}
