import {AfterViewInit, Component, OnInit} from "@angular/core";
import {LekarzDetailViewDto} from "@przychodnia/model/backend-model";
import {NotificationService} from "@przychodnia/service/notification/notification.service";
import {LekarzService} from "@przychodnia/service/lekarz.service";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'mp-wyniki-wyszukiwania',
    templateUrl: './wyniki-wyszukiwania.component.html',
    styleUrls: ['./wyniki-wyszukiwania.component.scss']
})
export class WynikiWyszukiwaniaComponent implements OnInit, AfterViewInit {

    searchBy: string;
    lekarzCollection: Array<LekarzDetailViewDto>;

    constructor(private route: ActivatedRoute,
                private lekarzService: LekarzService,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {
        this.route.queryParamMap
            .subscribe(paramMap => {
                this.searchBy = paramMap.get('searchBy');
            });
    }

    ngAfterViewInit(): void {
        this.search();
    }

    getExperienceInYears(rokUzyskaniaDyplomu: number): number {
        if (rokUzyskaniaDyplomu) {
            const now: Date = new Date();
            return now.getFullYear() - rokUzyskaniaDyplomu;
        }

        return null;
    }

    private search(): void {
        this.lekarzService.getLekarzList(this.searchBy).subscribe((lekarzListResponse: Array<LekarzDetailViewDto>) => {
            this.lekarzCollection = lekarzListResponse;
        });
    }


}
