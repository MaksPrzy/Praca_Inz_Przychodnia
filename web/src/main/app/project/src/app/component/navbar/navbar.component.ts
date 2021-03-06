import {Component, OnInit} from "@angular/core";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";
import {PacjentDetailViewDto} from "@przychodnia/model/backend-model";

@Component({
    selector: 'mp-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

    uzytkownik: PacjentDetailViewDto;

    constructor(private uzytkownikService: UzytkownikService) {

    }

    ngOnInit(): void {
        this.uzytkownikService.user$.subscribe(uzytkownik => this.uzytkownik = uzytkownik);
    }

    onLogout(): void {
        this.uzytkownikService.logOut();
    }

}
