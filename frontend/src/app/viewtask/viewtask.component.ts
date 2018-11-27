import { Component, OnInit } from '@angular/core';
import { ActivatedRoute , Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DatePipe} from '@angular/common';


@Component({
  selector: 'app-viewtask',
  templateUrl: './viewtask.component.html',
  styleUrls: ['./viewtask.component.css']
})
export class ViewtaskComponent implements OnInit {
	
  task = {};	

  constructor(private route : ActivatedRoute , private http: HttpClient,private datePipe : DatePipe) { }

  ngOnInit() {
	  this.getTaskDetail(this.route.snapshot.params['id']);
  }
  
   getTaskDetail(id){
	  this.http.get('/tasks/'+id).subscribe(data => {
		 this.task = data;
		 this.updateStartDateFormat(this.task);
		 
	  });
  }
  
   updateStartDateFormat(task){
	  task.startDate = this.datePipe.transform(task.startDate , "yyyy-MM-dd");
  }

}
