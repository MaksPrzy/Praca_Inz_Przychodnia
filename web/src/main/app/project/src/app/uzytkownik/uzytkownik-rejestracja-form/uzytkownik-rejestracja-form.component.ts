import {Component} from "@angular/core";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";
import {PacjentNewDto} from "@przychodnia/model/backend-model";
import {FormControl, FormGroup} from "@angular/forms";

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
        adres: new FormControl()
    })

    constructor(private uzytkownikService: UzytkownikService) {
    }

    onRegister(): void {
        const newUzytkownik: PacjentNewDto = {
            pesel: this.registerForm.get('pesel').value,
            imie: this.registerForm.get('imie').value,
            nazwisko: this.registerForm.get('nazwisko').value,
            email: this.registerForm.get('email').value,
            haslo: this.registerForm.get('haslo').value,
            telefonKomorkowy: this.registerForm.get('telefon').value,
            dataUrodzenia: this.registerForm.get('data urodzenia').value,
            adres: (this.registerForm.get('kodPocztowy'),
                    this.registerForm.get('miejscowosc'),
                    this.registerForm.get('ulica'),
                    this.registerForm.get('numerDomu')).value,
        }
        this.uzytkownikService.register(newUzytkownik)
    }

}
