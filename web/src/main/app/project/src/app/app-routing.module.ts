import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from "./component/page-not-found/page-not-found.component";
import {GabinetListComponent} from "./gabinet/component/gabinet-list/gabinet-list.component";

const routes: Routes = [
    {
        path: 'gabinety',
        component: GabinetListComponent
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
