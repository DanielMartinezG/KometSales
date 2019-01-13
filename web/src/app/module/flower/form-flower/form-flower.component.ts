import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import {  FileUploader } from 'ng2-file-upload/ng2-file-upload';


@Component({
  selector: 'app-form-flower',
  templateUrl: './form-flower.component.html',
  styleUrls: ['./form-flower.component.scss']
})
export class FormFlowerComponent implements OnInit {

  formFlower: FormGroup;
  message: string;

  constructor(private route : ActivatedRoute,
    private router : Router, 
    private formBuilder: FormBuilder,) { }

  public uploader: FileUploader = new FileUploader({url: "http://localhost:8080/flower/upload", 
    itemAlias: 'multiPartFile'});

  ngOnInit() {
    this.message = '';
    this.formFlower = this.getForm();

    this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
         this.uploader.clearQueue();
     };
  }

  onSubmit(){
    if(this.controlFlower.controls.email.errors == null){
      this.uploader.options.additionalParameter = { "email": this.formFlower.value.email }
      this.uploader.uploadAll();
    }
  }

  get controlFlower(){
    return <FormGroup> this.formFlower;
  }

  private getForm() : FormGroup{
    return this.formBuilder.group({
      email : ['', [Validators.required, Validators.maxLength(50), Validators.email]]	        
    })
  }
  

  readFile(fileEvent: any){
    const file = fileEvent.target.files[0];
    const extFile = this.getExtFile(file.name);
  }

  getExtFile (name: string) : string{
    var ext;
    const point = name.indexOf(".") ;    
    if(point > 0){
      ext = name.substring(point + 1, name.length);
    }
    return ext;
  }
}

