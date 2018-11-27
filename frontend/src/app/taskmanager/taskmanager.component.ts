import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute , Router } from '@angular/router';

@Component({
  selector: 'app-taskmanager',
  templateUrl: './taskmanager.component.html',
  styleUrls: ['./taskmanager.component.css']
})
export class TaskmanagerComponent implements OnInit {
	
  tasks: any;
  searchTask = {} ;

  constructor(private http:HttpClient ,private router: Router , private route: ActivatedRoute) { }

  ngOnInit() {
	  this.http.get('/tasks').subscribe(data => {
		  this.tasks = data;
	  });
  }
  
  endTask(id){
	  this.http.get('/tasks/endtask/'+id)
	  .subscribe(res => {
		this.tasks = res;
	  });
	  
  }

   searchTaskDetails(){
	  this.http.post('/tasks/searchtask',this.searchTask)
	  .subscribe(res => {
		this.tasks = res;
     });
	  
  }
  clearsearchDetails(){
     this.searchTask = {};
	  this.http.get('/tasks').subscribe(data => {
		  this.tasks = data;
	  });
	  
  }
   
  

}
