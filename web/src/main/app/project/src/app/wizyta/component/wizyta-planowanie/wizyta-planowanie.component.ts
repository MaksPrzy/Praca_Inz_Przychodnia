import {Component, OnInit} from "@angular/core";
import {LekarzService} from "@przychodnia/service/lekarz.service";
import {
    AbstractHarmonogramPozycjaDto,
    HarmonogramViewDto,
    HarmonogramZaplanowanaWizytaDto,
    LekarzDetailViewDto,
    LekarzSpecjalizacjaViewDto,
    PacjentDetailViewDto,
    WizytaViewDto,
    ZaplanujWizyteDto
} from "@przychodnia/model/backend-model";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {WizytaService} from "@przychodnia/service/wizyta.service";
import {NotificationService} from "@przychodnia/service/notification/notification.service";
import {catchError, map, switchMap, tap} from "rxjs/operators";
import {EMPTY} from "rxjs";

@Component({
    selector: 'mp-wizyta-planowanie',
    templateUrl: './wizyta-planowanie.component.html',
    styleUrls: ['./wizyta-planowanie.component.scss']
})
export class WizytaPlanowanieComponent implements OnInit {

    harmonogram: HarmonogramViewDto;
    interval: number = 0;
    minGodzinaOd: number;
    maxGodzinaDo: number;
    minuteCollection: Array<number> = [];
    dayCollection: Array<string> = ['poniedziałek', 'wtorek', 'środa', 'czwartek', 'piątek', 'sobota', 'niedziela'];
    availableHours: any = {};

    currentWeek: Date;
    weekDateFrom: Date;
    changeWeekToPrevLinkVisible: boolean;
    weekDateTo: Date;
    changeWeekToNextLinkVisible: boolean;

    lekarz: LekarzDetailViewDto;
    specjalizacja: LekarzSpecjalizacjaViewDto;

    zaplanowaneWizytyNaTydzien: Array<HarmonogramZaplanowanaWizytaDto>;

    constructor(private router: Router,
                private activatedRoute: ActivatedRoute,
                private lekarzService: LekarzService,
                private uzytkownikService: UzytkownikService,
                private wizytaService: WizytaService,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {
        this.activatedRoute.paramMap.pipe(
            map(params => parseInt(params.get('lekarzId'))),
            switchMap(lekarzId => this.lekarzService.getLekarz(lekarzId)),
            switchMap(lekarzResponse => {
                this.lekarz = lekarzResponse;
                this.specjalizacja = lekarzResponse.specjalizacjaCollection.pop();
                return this.lekarzService.getHarmonogramList(lekarzResponse.id)
            }),
            switchMap(harmonogramCollectionResponse => {
                this.harmonogram = harmonogramCollectionResponse.pop();
                this.minGodzinaOd = this.getMinGodzinaOd(this.harmonogram.pozycjaCollection);
                this.maxGodzinaDo = this.getMaxGodzinaDo(this.harmonogram.pozycjaCollection);
                this.initMinuteCollection();
                this.initAvailableHours();
                this.currentWeek = new Date();
                this.weekDateFrom = this.getPreviousMonday(this.currentWeek);
                this.weekDateTo = this.getNextSunday(this.currentWeek);
                return this.wizytaService.getZaplanowanaWizytaNaTydzienList(this.weekDateFrom, this.weekDateTo, this.lekarz.id, this.specjalizacja.id);
            })
        ).subscribe(
            zaplanowanaWizytaResponse => {
                this.zaplanowaneWizytyNaTydzien = zaplanowanaWizytaResponse;
                this.initChangeWeekLinksVisibility();
            },
            err => this.notificationService.showError(err)
        );
    }

    loadZaplanowaneWizytyNaTydzien(): void {
        this.wizytaService.getZaplanowanaWizytaNaTydzienList(this.weekDateFrom, this.weekDateTo, this.lekarz.id, this.specjalizacja.id)
            .subscribe((zaplanowanaWizytaResponseDto: Array<HarmonogramZaplanowanaWizytaDto>) => {
                this.zaplanowaneWizytyNaTydzien = zaplanowanaWizytaResponseDto;
            })
    }

    onZaplanujWizyte(dayIndex: number, minute: number): void {
        if (this.uzytkownikService.isLoggedIn()) {
            const dataWizytyHour = this.getHour(minute + 60);
            const dataWizytyMinutes = this.getMinutesForHour(minute);

            let dataWizytyOd: Date = this.addDays(this.weekDateFrom, dayIndex - 1);
            dataWizytyOd.setHours(dataWizytyHour);
            dataWizytyOd.setMinutes(dataWizytyMinutes);
            dataWizytyOd.setSeconds(0);

            let dataWizytyDo: Date = this.addDays(this.weekDateFrom, dayIndex - 1);
            dataWizytyDo.setHours(dataWizytyHour);
            dataWizytyDo.setMinutes(dataWizytyMinutes + this.interval);
            dataWizytyDo.setSeconds(0);

            const dayInfo: AbstractHarmonogramPozycjaDto = this.getDayInfo(dayIndex, minute);

            this.planujWizyteWhenUserLoggedIn(dataWizytyOd, dataWizytyDo, dayInfo.gabinet.id);
        } else {
            this.planujWizyteWhenUserNotLoggedIn();
        }
    }

    private planujWizyteWhenUserLoggedIn(dataWizytyOd: Date, dataWizytyDo: Date, gabinetId: number) {
        const uzytkownik: PacjentDetailViewDto = this.uzytkownikService.getUzytkownik();

        const zaplanujWizyteDto: ZaplanujWizyteDto = {
            pacjentId: uzytkownik.id,
            lekarzId: this.lekarz.id,
            specjalizacjaId: this.specjalizacja.id,
            gabinet: gabinetId,
            dataWizytyOd: dataWizytyOd,
            dataWizytyDo: dataWizytyDo,
            rodzaj: 'prywatna'
        };

        this.wizytaService.zaplanuj(zaplanujWizyteDto).subscribe((wizytaViewDto: WizytaViewDto) => {
            this.notificationService.showSuccess('Twoja wizyta została zaplanowana.');
            this.router.navigate(['/podsumowanie-wizyty', wizytaViewDto.id]);
        });
    }

    private planujWizyteWhenUserNotLoggedIn() {
        this.router.navigate(['logowanie'], {queryParams: {baseUrl: 'planowanie-wizyty'}});
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

    initChangeWeekLinksVisibility(): void {
        this.changeWeekToPrevLinkVisible = new Date(this.currentWeek).getTime() >= new Date(this.harmonogram.obowiazujeOd).getTime();
        this.changeWeekToNextLinkVisible = new Date(this.currentWeek).getTime() <= new Date(this.harmonogram.obowiazujeDo).getTime();
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

        // pobiera z harmonogramu pracy lekarza / specjalizacji
        let available: boolean = minute >= this.availableHours[dayIndex]['minutaOd'] && minute <= this.availableHours[dayIndex]['minutaDo'];

        if (this.zaplanowaneWizytyNaTydzien) {
            // sprawdzenie czy inny pacjent nie wybral terminu
            const zajetyTermin: boolean = this.zaplanowaneWizytyNaTydzien
                .filter(w => w.dayIndex === dayIndex)
                .filter(w => minute >= w.minuteFrom)
                .filter(w => minute < w.minuteTo).length > 0;

            available = available && !zajetyTermin;
        }

        return available;
    }


    getMinutesAsHours(minutes: number): string {
        const hours: number = this.getHour(minutes);
        const mins: number = this.getMinutesForHour(minutes);

        return hours + ':' + ((mins < 10) ? '0' + mins : mins);
    }

    private getHour(minutes: number): number {
        return Math.floor(minutes / 60);
    }

    private getMinutesForHour(minutes: number): number {
        return minutes - (this.getHour(minutes) * 60);
    }

    onChangeWeek(prevWeek: boolean): void {
        const days: number = 7 * (prevWeek ? -1 : 1);
        this.weekDateFrom = this.addDays(this.weekDateFrom, days);
        this.weekDateTo = this.addDays(this.weekDateTo, days);
        this.currentWeek = new Date(this.weekDateFrom);

        this.loadZaplanowaneWizytyNaTydzien();
        this.initChangeWeekLinksVisibility();
    }

    private initMinuteCollection(): void {
        if (this.harmonogram && this.minGodzinaOd && this.maxGodzinaDo) {
            this.interval = this.harmonogram.pozycjaCollection[0].interwalCzasowyWMinutach;
            let from: number = this.minGodzinaOd;

            while (from <= this.maxGodzinaDo) {
                this.minuteCollection.push(from);
                from = from + this.interval;
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

    private getPreviousMonday(date: Date): Date {
        let day = date.getDay() - 1;
        let prevMonday;

        if (date.getDay() == 0) {
            prevMonday = new Date().setDate(date.getDate() - 7);
        } else {
            prevMonday = new Date().setDate(date.getDate() - day);
        }

        return new Date(prevMonday);
    }

    private getNextSunday(date: Date): Date {
        let day = date.getDay();
        let nextSunday;

        if (date.getDay() == 7) {
            nextSunday = new Date().setDate(date.getDate() + 7);
        } else {
            nextSunday = new Date().setDate(date.getDate() + (7 - day));
        }

        return new Date(nextSunday);
    }

    private addDays(date, days): Date {
        let result: Date = new Date(date);
        result.setDate(result.getDate() + days);
        return result;
    }

}
