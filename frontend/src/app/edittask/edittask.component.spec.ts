import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EdittaskComponent } from './edittask.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { DatePipe } from '@angular/common';
import { MomentModule } from 'angular2-moment';
import { ActivatedRoute , Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { HttpClientModule } from '@angular/common/http';

import { RouterTestingModule} from '@angular/router/testing';

describe('EdittaskComponent', () => {
  let component: EdittaskComponent;
  let fixture: ComponentFixture<EdittaskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
	 imports: [
        FormsModule,
        RouterModule,
        MomentModule,
		HttpClientModule,
		RouterTestingModule		
      ],
      providers: [ DatePipe ],	
      declarations: [ EdittaskComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EdittaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
     expect(component).toBeTruthy();
  });
});
