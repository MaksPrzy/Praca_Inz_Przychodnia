import {Injectable} from '@angular/core';
import {MessageService} from 'primeng/api';

enum NotificationSeverity {
  INFO = 'info',
  SUCCESS = 'success',
  WARN = 'warn',
  ERROR = 'error'
}

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private readonly DEFAULT_TOAST_LIFE: number = 3000;

  private readonly DEFAULT_TOAST_KEY: string = 'defaultToast';
  private readonly DEFAULT_TOAST_TITLE: string = 'Komunikat';

  private readonly ERROR_WARNING_TOAST_KEY: string = 'errorWarningToast';
  private readonly ERROR_WARNING_TOAST_WARN_TITLE: string = 'Ostrzeżenie';
  private readonly ERROR_WARNING_TOAST_ERROR_TITLE: string = 'Błąd';
  private readonly ERROR_WARNING_TOAST_LIFE: number = 5000;

  constructor(private messageService: MessageService) { }

  public showInfo(message: string): void {
    this.showMessage(this.DEFAULT_TOAST_KEY, NotificationSeverity.INFO, this.DEFAULT_TOAST_TITLE, message);
  }

  public showSuccess(message: string, title?: string): void {
    this.showMessage(this.DEFAULT_TOAST_KEY, NotificationSeverity.SUCCESS, title ? title : this.DEFAULT_TOAST_TITLE, message);
  }

  public showWarning(message: string): void {
    this.showMessage(this.ERROR_WARNING_TOAST_KEY, NotificationSeverity.WARN, this.ERROR_WARNING_TOAST_WARN_TITLE, message, this.ERROR_WARNING_TOAST_LIFE);
  }

  public showError(message: string): void {
    this.showMessage(this.ERROR_WARNING_TOAST_KEY, NotificationSeverity.ERROR, this.ERROR_WARNING_TOAST_ERROR_TITLE, message, this.ERROR_WARNING_TOAST_LIFE);
  }

  private showMessage(toastKey: string, severity: NotificationSeverity, title: string, message: string, life?: number): void {
    if (!message) {
      alert('Nie podano treści komunikatu dla "toast".');
    } else {
      this.messageService.add({
        key: toastKey,
        severity: severity,
        summary: title,
        detail: message,
        life: life || this.DEFAULT_TOAST_LIFE
      });
    }
  }

}
