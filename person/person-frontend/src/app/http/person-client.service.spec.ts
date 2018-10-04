import {TestBed} from '@angular/core/testing';

import {PersonClientService} from './person-client.service';

describe('PersonClientService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PersonClientService = TestBed.get(PersonClientService);
    expect(service).toBeTruthy();
  });
});
