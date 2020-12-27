import { Component, OnInit } from '@angular/core';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzListModule } from 'ng-zorro-antd/list';
@Component({
  selector: 'app-manage-project',
  templateUrl: './manage-project.component.html',
  styleUrls: ['./manage-project.component.css']
})
export class ManageProjectComponent implements OnInit {
  data: string[] = [
      'Racing car sprays burning fuel into crowd.',
      'Japanese princess to wed commoner.',
      'Australian walks 100km after outback crash.',
      'Man charged over missing wedding girl.',
      'Los Angeles battles huge wildfires.'
    ];
  msg: NzMessageService;
  constructor() {}

  ngOnInit(): void {}

  addNewProject(): void{
    console.log("New Project!");
  }

  editProject(): void{
    console.log("Goes to the editor");
  }


  deleteProject(): void{
    console.log("Deletes the project");
  }
}
