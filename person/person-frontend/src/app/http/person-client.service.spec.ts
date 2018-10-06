import {TestBed} from '@angular/core/testing';

import {PersonClientService} from './person-client.service';
import {Partner} from '../model';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

describe('PersonClientService', () => {

  let httpMock: HttpTestingController;
  let client: PersonClientService;
  const expectedPartner: Partner = {};


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [
        PersonClientService
      ]
    });
    httpMock = TestBed.get(HttpTestingController);
    client = TestBed.get(PersonClientService);
  });

  it('should be created', () => {
    const service: PersonClientService = TestBed.get(PersonClientService);
    expect(service).toBeTruthy();
  });

  it('should call http client', (done) => {
    client.createPartner({}).subscribe(
      (partner) => {
        expect(partner).toBe(expectedPartner);
        done();
      },
      (error) => {
        done.fail(error);
      }
    );
    const req = httpMock.expectOne({
      url: '/person/rest/api/v1/partners',
      method: 'POST'
    });
    req.flush(expectedPartner);
  });
});
