import {AfterViewInit, Component} from "@angular/core";
import {NotificationService} from "@przychodnia/service/notification/notification.service";

@Component({
    selector: 'mp-uzytkownik-logowanie-form',
    templateUrl: './uzytkownik-logowanie-form.component.html',
    styleUrls: ['./uzytkownik-logowanie-form.component.scss']
})
export class UzytkownikLogowanieFormComponent implements AfterViewInit {

    constructor(private notificationService: NotificationService) {
    }

    ngAfterViewInit(): void {

    }




}
