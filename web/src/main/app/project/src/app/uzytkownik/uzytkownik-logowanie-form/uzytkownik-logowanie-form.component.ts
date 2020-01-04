import {Component} from "@angular/core";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";
import {ZalogujDto} from "@przychodnia/model/backend-model";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
    selector: 'mp-uzytkownik-logowanie-form',
    templateUrl: './uzytkownik-logowanie-form.component.html',
    styleUrls: ['./uzytkownik-logowanie-form.component.scss']
})
export class UzytkownikLogowanieFormComponent {

    loginForm: FormGroup = new FormGroup({
        email: new FormControl(),
        haslo: new FormControl()
    });

    constructor(private uzytkownikService: UzytkownikService) {
    }

    onLogin(): void {
        const zalogujDto: ZalogujDto = {
            username: this.loginForm.get('email').value,
            password: this.loginForm.get('haslo').value
        };

        this.uzytkownikService.logIn(zalogujDto);
    }

}
