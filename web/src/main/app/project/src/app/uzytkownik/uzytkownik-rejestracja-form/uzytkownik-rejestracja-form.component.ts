import {AfterViewInit, Component} from "@angular/core";
import {NotificationService} from "@przychodnia/service/notification/notification.service";

@Component({
    selector: 'mp-uzytkownik-rejestracja-form',
    templateUrl: './uzytkownik-rejestracja-form.component.html',
    styleUrls: ['./uzytkownik-rejestracja-form.component.scss']
})
export class UzytkownikRejestracjaFormComponent implements AfterViewInit {

    constructor(private notificationService: NotificationService) {
    }

    ngAfterViewInit(): void {
    }

}
