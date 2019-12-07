import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from "./component/page-not-found/page-not-found.component";
import {GabinetListComponent} from "./gabinet/component/gabinet-list/gabinet-list.component";
import {UzytkownikRejestracjaFormComponent} from "./uzytkownik/uzytkownik-rejestracja-form/uzytkownik-rejestracja-form.component";
import {StartPageComponent} from "./component/start-page/start-page.component";
import {UzytkownikLogowanieFormComponent} from "./uzytkownik/uzytkownik-logowanie-form/uzytkownik-logowanie-form.component";
import {WynikiWyszukiwaniaComponent} from "./wyniki-wyszukiwania/wyniki-wyszukiwania.component";
import {WizytaPlanowanieComponent} from "./wizyta/component/wizyta-planowanie/wizyta-planowanie.component";


const routes: Routes = [
    // {
    //     path: '',
    //     redirectTo: '',
    //     pathMatch: 'full'
    // },
    {
        path: 'logowanie',
        component: UzytkownikLogowanieFormComponent
    },
    {
        path: 'planowanie-wizyty',
        component: WizytaPlanowanieComponent
    },
    {
        path: 'home',
        component: StartPageComponent
    },
    {
        path: 'rejestracja',
        component: UzytkownikRejestracjaFormComponent
    },
    {
        path: 'gabinety',
        component: GabinetListComponent
    },
    {
        path: 'wyniki-wyszukiwania',
        component: WynikiWyszukiwaniaComponent
    },
    {
        path: '**',
        component: PageNotFoundComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {
        onSameUrlNavigation: 'ignore',
        enableTracing: false
    })],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
