import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-error-message',
  templateUrl: './error-message.component.html',
  styleUrls: ['./error-message.component.css']
})
export class ErrorMessageComponent {

  @Input() control: FormControl;
  @Input() type: 'dirty' | 'touched';
  @Input() error: string;
  @Input() text: string;

  hasError(): boolean {
    if (this.type === 'dirty') {
      return this.control.hasError(this.error) && this.control.dirty;
    } else if (this.type === 'touched') {
      return this.control.hasError(this.error) && this.control.touched;
    }
  }

}
