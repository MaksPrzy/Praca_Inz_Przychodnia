import {Component, OnInit} from "@angular/core";
import {LekarzService} from "@przychodnia/service/lekarz.service";
import {AbstractHarmonogramPozycjaDto, HarmonogramViewDto} from "@przychodnia/model/backend-model";

@Component({
    selector: 'mp-wizyta-planowanie',
    templateUrl: './wizyta-planowanie.component.html',
    styleUrls: ['./wizyta-planowanie.component.scss']
})
export class WizytaPlanowanieComponent implements OnInit {

    harmonogram: HarmonogramViewDto;
    minGodzinaOd: number;
    maxGodzinaDo: number;
    minuteCollection: Array<number> = [];
    dayCollection: Array<string> = ['poniedziałek', 'wtorek', 'środa', 'czwartek', 'piątek', 'sobota', 'niedziela'];
    availableHours: any = {};

    constructor(private lekarzService: LekarzService) {
    }

    ngOnInit(): void {
        this.lekarzService.getHarmonogramList(2, 4)
            .subscribe((harmonogramCollectionResponse: Array<HarmonogramViewDto>) => {
                this.harmonogram = harmonogramCollectionResponse.pop();
                this.minGodzinaOd = this.getMinGodzinaOd(this.harmonogram.pozycjaCollection);
                this.maxGodzinaDo = this.getMaxGodzinaDo(this.harmonogram.pozycjaCollection);
                this.initMinuteCollection();
                this.initAvailableHours();
            });
    }

    initAvailableHours(): void {
        for (let pozycja of this.harmonogram.pozycjaCollection) {
            this.availableHours[pozycja.dzienTygodnia] = {
                minutaOd: this.getHoursAsMinutes(pozycja.godzinaOd),
                minutaDo: this.getHoursAsMinutes(pozycja.godzinaDo),
                dayInfo: pozycja
            };
        }
    }

    getDayInfo(dayIndex: number, minute: number): AbstractHarmonogramPozycjaDto {
        if (!this.availableHours[dayIndex]) {
            return;
        }

        if (this.isAvailableHour(dayIndex, minute)) {
            return this.availableHours[dayIndex]['dayInfo'];
        }
    }

    isAvailableHour(dayIndex: number, minute: number): boolean {
        if (!this.availableHours[dayIndex]) {
            return;
        }

        return minute >= this.availableHours[dayIndex]['minutaOd'] && minute <= this.availableHours[dayIndex]['minutaDo'];
    }

    getMinutesAsHours(minutes: number): string {
        const hours: number = Math.floor(minutes / 60);
        const mins: number = (minutes - (hours * 60));

        return hours + ':' + ((mins < 10) ? '0' + mins : mins);
    }

    private initMinuteCollection(): void {
        if (this.harmonogram && this.minGodzinaOd && this.maxGodzinaDo) {
            const interval = this.harmonogram.pozycjaCollection[0].interwalCzasowyWMinutach;
            let from: number = this.minGodzinaOd;

            while (from <= this.maxGodzinaDo) {
                this.minuteCollection.push(from);
                from = from + interval;
            }
        }
    }

    private getMinGodzinaOd(pozycjaCollection: Array<AbstractHarmonogramPozycjaDto>): number {
        let minGodzinaOd: number = this.getHoursAsMinutes(pozycjaCollection[0].godzinaOd);

        for (let pozycja of pozycjaCollection) {
            let checkedGodzinaOd: number = this.getHoursAsMinutes(pozycja.godzinaOd);

            if (checkedGodzinaOd < minGodzinaOd) {
                minGodzinaOd = checkedGodzinaOd;
            }
        }

        return minGodzinaOd;
    }

    private getMaxGodzinaDo(pozycjaCollection: Array<AbstractHarmonogramPozycjaDto>): number {
        let maxGodzinaDo: number = this.getHoursAsMinutes(pozycjaCollection[0].godzinaDo);

        for (let pozycja of pozycjaCollection) {
            let checkedGodzinaDo: number = this.getHoursAsMinutes(pozycja.godzinaDo);

            if (checkedGodzinaDo > maxGodzinaDo) {
                maxGodzinaDo = checkedGodzinaDo;
            }
        }

        return maxGodzinaDo;
    }

    private getHoursAsMinutes(date: any): number {
        try {
            return parseInt(date.substr(0, 2)) * 60 + parseInt(date.substr(3, 2));
        } catch (e) {
            return 0;
        }
    }

}
