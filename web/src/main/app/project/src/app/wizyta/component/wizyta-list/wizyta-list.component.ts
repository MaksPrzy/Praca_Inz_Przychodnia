import {AfterViewInit, Component} from "@angular/core";

@Component({
    selector: 'mp-wizyta-list',
    templateUrl: './wizyta-list.component.html',
    styleUrls: ['./wizyta-list.component.scss']
})
export class WizytaListComponent implements AfterViewInit {

    constructor() {
    }

    ngAfterViewInit(): void {
        this.loadWizytaCollection();
    }

    private loadWizytaCollection(): void {
    }

}
