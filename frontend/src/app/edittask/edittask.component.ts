import { Component, OnInit } from '@angular/core';
import { ActivatedRoute , Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DatePipe} from '@angular/common';

@Component({
  selector: 'app-edittask',
  templateUrl: './edittask.component.html',
  styleUrls: ['./edittask.component.css']
})
export class EdittaskComponent implements OnInit {
	
  task = {};
  
  constructor(private http: HttpClient , private router: Router , private route: ActivatedRoute ,private datePipe : DatePipe) { }

  ngOnInit() {
	  this.getTask(this.route.snapshot.params['id']);
  }
  getTask(id){
	  this.http.get('/tasks/'+id).subscribe(data => {
		 this.task = data;
		 this.updateStartDateFormat(this.task);
		 
	  });
  }
  
  updateStartDateFormat(task){
	  task.startDate = this.datePipe.transform(task.startDate , "yyyy-MM-dd");
  }
  
  updateTask(id){
	  this.http.post('/tasks/'+id ,this.task)
	  .subscribe(res => {
		  this.router.navigate(['/taskmanager',res]);
	  },(err) => {
		  console.log(err);
	  }
	  );
  }
	  
  	retrieveOldTask(){
		 this.getTask(this.route.snapshot.params['id']);
	}		
  
  

}
