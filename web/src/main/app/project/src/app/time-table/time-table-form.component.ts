import {AfterViewInit, Component} from "@angular/core";
import {NotificationService} from "@przychodnia/service/notification/notification.service";

@Component({
    selector: 'mp-time-table',
    templateUrl: './time-table-component.html',
    styleUrls: ['./time-table-component.scss']
})
export class TimeTableFormComponent implements AfterViewInit {

    constructor(private notificationService: NotificationService) {
    }

    ngAfterViewInit(): void {

    }

}
