import {Component} from "@angular/core";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";
import {ZalogujDto} from "@przychodnia/model/backend-model";
import {FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {NotificationService} from "@przychodnia/service/notification/notification.service";
import {map, switchMap} from "rxjs/operators";

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

    constructor(private router: Router,
                private activatedRoute: ActivatedRoute,
                private uzytkownikService: UzytkownikService,
                private notificationService: NotificationService) {
    }

    onLogin(): void {
        const zalogujDto: ZalogujDto = {
            username: this.loginForm.get('email').value,
            password: this.loginForm.get('haslo').value,
        };

        this.uzytkownikService.logIn(zalogujDto)
            .pipe(
                switchMap(() => this.activatedRoute.queryParamMap),
                map(paramMap => paramMap.get('baseUrl')),
            )
            .subscribe(
                baseUrl => {
                    return baseUrl ? this.router.navigateByUrl(baseUrl) : this.router.navigateByUrl('home');
                },
                err => this.notificationService.showError(err)
            );
    }

}
