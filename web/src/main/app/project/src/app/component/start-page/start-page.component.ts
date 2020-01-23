import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";

@Component({
    selector: 'mp-start',
    templateUrl: './start-page.component.html',
    styleUrls: ['./start-page.component.scss']
})
export class StartPageComponent implements OnInit {

    searchBy: string;

    constructor(private router: Router) {

    }

    ngOnInit(): void {
    }

    onSearch() {
        this.router.navigate(['wyniki-wyszukiwania'], {queryParams: {searchBy: this.searchBy}});
    }

}




