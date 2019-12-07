import {AfterViewInit, Component} from "@angular/core";
import {GabinetNewDto, GabinetViewDto} from "@przychodnia/model/backend-model";
import {GabinetService} from "@przychodnia/service/gabinet.service";
import {NotificationService} from "@przychodnia/service/notification/notification.service";

@Component({
    selector: 'mp-gabinet-list',
    templateUrl: './gabinet-list.component.html',
    styleUrls: ['./gabinet-list.component.scss']
})
export class GabinetListComponent implements AfterViewInit {

    gabinetCollection: Array<GabinetViewDto>;

    constructor(private gabinetService: GabinetService,
                private notificationService: NotificationService) {
    }

    ngAfterViewInit(): void {
        this.loadGabinetCollection();
    }

    private loadGabinetCollection(): void {
        this.gabinetService.getGabinetList().subscribe((gabinetCollectionResponse: Array<GabinetViewDto>) => {
            this.gabinetCollection = gabinetCollectionResponse;
        });
    }

    public onAddGabinet(): void {
        const gabinetNewDto: GabinetNewDto = {
            nazwa: 'Gabinet XYZ',
            opis: 'Gabinet zabiegowy',
            pietro: 9
        };

        this.gabinetService.add(gabinetNewDto).subscribe((gabinetViewDto: GabinetViewDto) => {
            this.notificationService.showSuccess(`Gabinet ${gabinetViewDto.nazwa} został dodany.`);
            this.loadGabinetCollection();
        });
    }

}
