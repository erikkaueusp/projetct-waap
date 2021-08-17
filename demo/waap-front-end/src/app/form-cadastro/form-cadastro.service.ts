import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { FormGroup } from '@angular/forms';

const API = "http://localhost:8080/users/upload";
@Injectable({
  providedIn: 'root',
})
export class FormCadastroService {
  constructor(private http: HttpClient) {}


  onUpload(selectedFile:File) {
    const uploadData = new FormData();
    uploadData.append('image', selectedFile, selectedFile.name);
    this.http.post(API, uploadData, {
      reportProgress: true,
      observe: 'events'
    })
      .subscribe(event => {
        console.log(event); // handle event here
      });
  }


  getPhoto(id:string) {
    return this.http.get(API + '/users/image/' + id);
    
  }

}
