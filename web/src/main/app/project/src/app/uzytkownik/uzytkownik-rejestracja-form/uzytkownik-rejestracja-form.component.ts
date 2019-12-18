import {AfterViewInit, Component} from "@angular/core";
import {NotificationService} from "@przychodnia/service/notification/notification.service";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";
import {PacjentNewDto} from "@przychodnia/model/backend-model";

@Component({
    selector: 'mp-uzytkownik-rejestracja-form',
    templateUrl: './uzytkownik-rejestracja-form.component.html',
    styleUrls: ['./uzytkownik-rejestracja-form.component.scss']
})
export class UzytkownikRejestracjaFormComponent {

    constructor(private uzytkownikService: UzytkownikService) {
    }

    onRegister(): void {
        // const newUzytkownik: PacjentNewDto = {
        //
        // }
        // this.uzytkownikService.register()
    }

}
