import {AfterViewInit, Component} from "@angular/core";
import {NotificationService} from "@przychodnia/service/notification/notification.service";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";
import {ZalogujDto} from "@przychodnia/model/backend-model";

@Component({
    selector: 'mp-uzytkownik-logowanie-form',
    templateUrl: './uzytkownik-logowanie-form.component.html',
    styleUrls: ['./uzytkownik-logowanie-form.component.scss']
})
export class UzytkownikLogowanieFormComponent implements AfterViewInit {

    constructor(private uzytkownikService: UzytkownikService) {
    }

    ngAfterViewInit(): void {
        const zalogujDto: ZalogujDto = {
            username: 'JAKISLOGIN',
            password: 'password'
        };

        this.uzytkownikService.logIn(zalogujDto);
    }

}
