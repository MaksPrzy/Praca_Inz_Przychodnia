import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from "./component/page-not-found/page-not-found.component";
import {GabinetListComponent} from "./gabinet/component/gabinet-list/gabinet-list.component";
import {UzytkownikRejestracjaFormComponent} from "./uzytkownik/uzytkownik-rejestracja-form/uzytkownik-rejestracja-form.component";
import {StartPageComponent} from "./component/start-page/start-page.component";
import {UzytkownikLogowanieFormComponent} from "./uzytkownik/uzytkownik-logowanie-form/uzytkownik-logowanie-form.component";
import {WynikiWyszukiwaniaComponent} from "./wyniki-wyszukiwania/wyniki-wyszukiwania.component";
import {WizytaPlanowanieComponent} from "./wizyta/component/wizyta-planowanie/wizyta-planowanie.component";
import {RodoComponent} from "./rodo/rodo.component";
import {WizytaListComponent} from "./wizyta/component/wizyta-list/wizyta-list.component";
import {WizytaPodsumowanieComponent} from "./wizyta/component/wizyta-podsumowanie/wizyta-podsumowanie.component";

const routes: Routes = [
    {
        path: '',
        redirectTo: '/home',
        pathMatch: 'full'
    },
    {
        path: 'logowanie',
        component: UzytkownikLogowanieFormComponent
    },
    {
        path: 'planowanie-wizyty',
        component: WizytaPlanowanieComponent
    },
    {
        path: 'moje-wizyty',
        component: WizytaListComponent
    },
    {
        path: 'podsumowanie-wizyty/:id',
        component: WizytaPodsumowanieComponent
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
        path: 'rodo',
        component: RodoComponent
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
