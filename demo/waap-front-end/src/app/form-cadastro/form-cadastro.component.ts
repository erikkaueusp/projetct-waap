import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators } from '@angular/forms';
import { FormCadastroService } from './form-cadastro.service';

@Component({
  templateUrl: './form-cadastro.component.html',
  styleUrls: ['./form-cadastro.component.css']
})
export class FormCadastroComponent implements OnInit {
  url = ''
  cadastroForm!: FormGroup;
  file: File;
  preview: string;

  constructor(
    private formBuilder: FormBuilder,
    private formcadastroService: FormCadastroService) { }

  ngOnInit() {
    this.cadastroForm = this.formBuilder.group({
      file: ['', Validators.required],
      nome: ['', Validators.maxLength(300)],
      email: ['', Validators.maxLength(300)],
      telefone: ['', Validators.maxLength(300)]

    })
  }

  teste(event:Event) {

    const readURL = file => {
      return new Promise((res, rej) => {
          const reader = new FileReader();
          reader.onload = event => res(event.target.result);
          reader.onerror = event => rej(e);
          reader.readAsDataURL(file);
      });
  };
}

  submit() {
    const nome:string = this.cadastroForm.get('nome').value;
    const email:string = this.cadastroForm.get('email').value;
    const telefone:number = this.cadastroForm.get('email').value;
  }


  handleFile(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files as FileList;
    console.log(files);
    const reader = new FileReader();
    reader.onload = (event: any) => this.preview = event.target.result;
    // reader.readAsDataURL(files);
  }

}
