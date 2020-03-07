import {Component, OnInit} from "@angular/core";
import {WizytaService} from "@przychodnia/service/wizyta.service";
import {WizytaViewDto} from "@przychodnia/model/backend-model";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";
import {Observable} from "rxjs";

@Component({
    selector: 'mp-wizyta-list',
    templateUrl: './wizyta-list.component.html',
    styleUrls: ['./wizyta-list.component.scss']
})
export class WizytaListComponent implements OnInit {

    wizytaCollection$: Observable<Array<WizytaViewDto>>;

    constructor(private wizytaService: WizytaService,
                private uzytkownikService: UzytkownikService) {
    }

    ngOnInit(): void {
        this.wizytaCollection$ = this.wizytaService.getWizytaList(
            this.uzytkownikService.user.id
        );
    }

}
