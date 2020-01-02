import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";

@Component({
    selector: 'mp-start',
    templateUrl: './start-page.component.html',
    styleUrls: ['./start-page.component.scss']
})
export class StartPageComponent implements OnInit{

    constructor(private router: Router){};
    searchBy: string;

    ngOnInit(): void {
    }

    test(){
        console.log('test');
        this.router.navigate(['/wyniki-wyszukiwania'], { queryParams: { searchBy: this.searchBy} });
    }
}




