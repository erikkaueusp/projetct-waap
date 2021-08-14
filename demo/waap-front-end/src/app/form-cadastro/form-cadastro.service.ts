import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

const API = 'http://localhost:8081';
@Injectable({
  providedIn: 'root',
})
export class FormCadastroService {
  constructor(private http: HttpClient) {}

  upload(file: File) {
    const formData = new FormData();
    formData.append('foto', file);

    return this.http.post(API + '/fotos', formData);
  }
}
