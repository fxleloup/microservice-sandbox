import {Injectable} from '@angular/core';
import {Partner} from '../model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonClientService {

  constructor(private http: HttpClient) {
  }

  public createPartner(partner: Partner): Observable<Partner> {
    return this.http.post<Partner>('/person/rest/api/v1/partners', partner)
  }
}
