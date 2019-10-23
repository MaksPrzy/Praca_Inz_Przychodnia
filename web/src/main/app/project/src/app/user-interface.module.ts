import {NgModule} from "@angular/core";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/primeng";
import {ToastModule} from "primeng/toast";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
    imports: [
        BrowserAnimationsModule,
        FormsModule,

        ButtonModule,
        InputTextModule,
        ToastModule
    ],
    exports: [
        FormsModule,
        BrowserAnimationsModule,

        ButtonModule,
        InputTextModule,
        ToastModule
    ]
})
export class UserInterfaceModule {

}
