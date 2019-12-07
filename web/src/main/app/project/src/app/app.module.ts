import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {MessageService} from "primeng/primeng";
import {AppRequestInterceptor} from "./interceptor/app-request.interceptor";
import {ContentComponent} from "./component/content/content.component";
import {UserInterfaceModule} from "./user-interface.module";
import {PageNotFoundComponent} from "./component/page-not-found/page-not-found.component";
import {GabinetListComponent} from "./gabinet/component/gabinet-list/gabinet-list.component";
import {GabinetService} from "./service/gabinet.service";
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {NavbarComponent} from "./component/navbar/navbar.component";
import {UzytkownikRejestracjaFormComponent} from "./uzytkownik/uzytkownik-rejestracja-form/uzytkownik-rejestracja-form.component";
import {StartPageComponent} from "./component/start-page/start-page.component";
import {LekarzService} from "@przychodnia/service/lekarz.service";
import {UzytkownikLogowanieFormComponent} from "./uzytkownik/uzytkownik-logowanie-form/uzytkownik-logowanie-form.component";
import {WynikiWyszukiwaniaComponent} from "./wyniki-wyszukiwania/wyniki-wyszukiwania.component";
import {TimeTableFormComponent} from "./time-table/time-table-form.component";

@NgModule({
    declarations: [
        AppComponent,
        ContentComponent,
        NavbarComponent,
        GabinetListComponent,
        UzytkownikRejestracjaFormComponent,
        UzytkownikLogowanieFormComponent,
        StartPageComponent,
        WynikiWyszukiwaniaComponent,
        PageNotFoundComponent,
        TimeTableFormComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        MatToolbarModule,
        UserInterfaceModule,

        NoopAnimationsModule
    ],
    providers: [
        MessageService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AppRequestInterceptor,
            multi: true
        },
        GabinetService,
        LekarzService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {

}
