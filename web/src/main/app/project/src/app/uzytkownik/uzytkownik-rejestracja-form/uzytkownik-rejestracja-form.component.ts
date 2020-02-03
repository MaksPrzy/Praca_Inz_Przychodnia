import {Component} from "@angular/core";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";
import {PacjentDetailViewDto, PacjentRejestracjaDto, ZalogujDto} from "@przychodnia/model/backend-model";
import {FormControl, FormGroup} from "@angular/forms";
import {NotificationService} from "@przychodnia/service/notification/notification.service";

@Component({
    selector: 'mp-uzytkownik-rejestracja-form',
    templateUrl: './uzytkownik-rejestracja-form.component.html',
    styleUrls: ['./uzytkownik-rejestracja-form.component.scss']
})
export class UzytkownikRejestracjaFormComponent {

    registerForm: FormGroup = new FormGroup({
        pesel: new FormControl(),
        imie: new FormControl(),
        nazwisko: new FormControl(),
        email: new FormControl(),
        haslo: new FormControl(),
        telefon: new FormControl(),
        dataUrodzenia: new FormControl(),
        kodPocztowy: new FormControl(),
        miejscowosc: new FormControl(),
        ulica: new FormControl(),
        numerDomu: new FormControl()
    })

    constructor(private uzytkownikService: UzytkownikService, private notificationService: NotificationService) {
    }

    onRegister(): void {
        const newUzytkownik: PacjentRejestracjaDto = {
            imie: this.registerForm.get('imie').value,
            nazwisko: this.registerForm.get('nazwisko').value,
            pesel: this.registerForm.get('pesel').value,
            dataUrodzenia: this.registerForm.get('dataUrodzenia').value,
            email: this.registerForm.get('email').value,
            haslo: this.registerForm.get('haslo').value,
            telefonKomorkowy: this.registerForm.get('telefon').value,
            adres: {
                kodPocztowy: this.registerForm.get('kodPocztowy').value,
                miejscowosc: this.registerForm.get('miejscowosc').value,
                ulica: this.registerForm.get('ulica').value,
                numerDomu: this.registerForm.get('numerDomu').value
            }
        };

        console.log('uzytkownik data');
        console.dir(newUzytkownik);

        this.uzytkownikService.registerIn(newUzytkownik).subscribe(
            (pacjentDetailViewDto: PacjentDetailViewDto) => {
                this.notificationService.showInfo('Zostałeś zarejestrowany.');
            },
            (error: any) => {
                this.notificationService.showError(error);
            },
            () => {
                const zalogujDto: ZalogujDto = {
                    username: newUzytkownik.email,
                    password: newUzytkownik.haslo
                };

                this.uzytkownikService.logIn(zalogujDto);
            }
        );
    }

}
