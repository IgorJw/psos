import { Component, ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  
  isRightPanelActive = false;

  togglePanel() {
    this.isRightPanelActive = !this.isRightPanelActive;
  }
}
