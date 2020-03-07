import {AfterViewChecked, AfterViewInit, Component, OnInit} from "@angular/core";
import {UzytkownikService} from "@przychodnia/service/uzytkownik.service";
import {PacjentDetailViewDto} from "@przychodnia/model/backend-model";

@Component({
    selector: 'mp-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, AfterViewInit {

    uzytkownik: PacjentDetailViewDto;

    constructor(private uzytkownikService: UzytkownikService) {

    }

    ngOnInit(): void {
        this.uzytkownik = this.uzytkownikService.getUzytkownik();
    }

    ngAfterViewInit(): void {
        this.uzytkownik = this.uzytkownikService.getUzytkownik();
    }

    onLogout(): void {
        this.uzytkownikService.logOut();
    }

}
