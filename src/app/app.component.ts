import { Component, AfterViewInit , ViewChild, ElementRef, HostListener } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TomcatService } from './services/Tomcat/tomcat.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'My Medics Expert – Consultation';

  gitdata: any;
  @ViewChild('stickyMenu') menuElement: ElementRef;
  sticky = false;
  elementPosition: any;

  images = ['/src/images/medics-1.png',
    '/src/images/medics-2.png',
    '/src/images/6.png', '/src/images/19.png',
    '/src/images/19.png',
    '/src/images/19.png'];

  constructor(private _tomcatservice: TomcatService) { }

  getData(): void {
    this.gitdata = this._tomcatservice.getData().subscribe((response) => {
      this.gitdata = response;
      console.log('data recieved' + JSON.stringify(response));
    });

    console.log('Component::' + this.gitdata);
  }
  /*getData(): void {
     this._httpclient.get(this.apiURL).subscribe((response) => {
      this.gitdata = response;
      console.log('data recieved'+JSON.stringify(response));
    });

  }*/

  // tslint:disable-next-line:use-life-cycle-interface
  ngAfterViewInit() {
    this.elementPosition = this.menuElement.nativeElement.offsetTop;
  }

  @HostListener('window:scroll', ['$event'])
    handleScroll() {
      const windowScroll = window.pageYOffset;
      if (windowScroll >= this.elementPosition) {
        this.sticky = true;
      } else {
        this.sticky = false;
      }
}

}
