import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {MessageService} from "primeng/primeng";
import {AppRequestInterceptor} from "./interceptor/app-request.interceptor";
import {ContentComponent} from "./component/content/content.component";
import {UserInterfaceModule} from "./user-interface.module";
import {PageNotFoundComponent} from "./component/page-not-found/page-not-found.component";
import {GabinetListComponent} from "./gabinet/component/gabinet-list/gabinet-list.component";
import {GabinetService} from "./gabinet/gabinet.service";

@NgModule({
    declarations: [
        AppComponent,
        ContentComponent,
        GabinetListComponent,
        PageNotFoundComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,

        UserInterfaceModule
    ],
    providers: [
        MessageService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AppRequestInterceptor,
            multi: true
        },
        GabinetService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {

}
