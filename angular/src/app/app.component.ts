import { Component, ElementRef, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular';

  constructor(private elementRef: ElementRef, private renderer: Renderer2) { }

  ngAfterViewInit() {
    const menuOpen = this.elementRef.nativeElement.querySelector(".menu");
    const menuClose = this.elementRef.nativeElement.querySelector(".close");
    const overlay = this.elementRef.nativeElement.querySelector(".overlay");

    this.renderer.listen(menuOpen, 'click', () => {
      this.renderer.addClass(overlay, 'overlay--active');
    });

    this.renderer.listen(menuClose, 'click', () => {
      this.renderer.removeClass(overlay, 'overlay--active');
    });
  }


}
