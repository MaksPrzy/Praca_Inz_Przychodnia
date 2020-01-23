import {Component, OnInit} from "@angular/core";
import {LekarzService} from "@przychodnia/service/lekarz.service";
import {
    AbstractHarmonogramPozycjaDto, AbstractLekarzSpecjalizacjaDto,
    HarmonogramViewDto, LekarzDetailViewDto, LekarzSpecjalizacjaViewDto, PacjentDetailViewDto,
    WizytaViewDto,
    ZaplanujWizyteDto
} from "@przychodnia/model/backend-model";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";
import {Router} from "@angular/router";
import {WizytaService} from "@przychodnia/service/wizyta.service";

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

    lekarz: LekarzDetailViewDto;
    specjalizacja: LekarzSpecjalizacjaViewDto;

    constructor(private router: Router,
                private lekarzService: LekarzService,
                private uzytkownikService: UzytkownikService,
                private wizytaService: WizytaService) {
    }

    ngOnInit(): void {
        // todo wczytac informacje o lekarzu
        this.specjalizacja = {
            id: 4,
            nazwa: 'specjalizacjaLekarzaNazwa',
            rokUzyskaniaDyplomuZeSpecjalizacji: 2000
        };

        this.lekarz = {
            id: 2,
            imie: 'imieLekarza',
            nazwisko: 'nazwiskoLekarza',
            numer: 'numerLekarza',
            specjalizacjaCollection: []
        };

        this.lekarzService.getHarmonogramList(this.lekarz.id, this.specjalizacja.id)
            .subscribe((harmonogramCollectionResponse: Array<HarmonogramViewDto>) => {
                this.harmonogram = harmonogramCollectionResponse.pop();
                this.minGodzinaOd = this.getMinGodzinaOd(this.harmonogram.pozycjaCollection);
                this.maxGodzinaDo = this.getMaxGodzinaDo(this.harmonogram.pozycjaCollection);
                this.initMinuteCollection();
                this.initAvailableHours();
            });
    }

    onZaplanujWizyte(dayIndex: number, minute: number): void {
        if (this.uzytkownikService.isLoggedIn()) {
            // todo pobrac daty
            const dataWizytyOd: Date = new Date();
            const dataWizytyDo: Date = new Date();
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
