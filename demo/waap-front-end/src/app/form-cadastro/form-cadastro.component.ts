import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { FormCadastroService } from './form-cadastro.service';




@Component({
  templateUrl: './form-cadastro.component.html',
  styleUrls: ['./form-cadastro.component.css']
})
export class FormCadastroComponent implements OnInit {



  form: FormGroup;
  API = "http://localhost:8080/users/upload";
  selectedFile: File;

  constructor(private formcadastroService: FormCadastroService) { }

  ngOnInit() { }

  onFileChanged(event:any) {
    this.selectedFile = event.target.files[0]
  }

  onUpload() {
    this.formcadastroService.onUpload(this.selectedFile);
  }



}

