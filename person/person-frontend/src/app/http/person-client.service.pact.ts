import {TestBed} from '@angular/core/testing';
import {PersonClientService} from './person-client.service';
import {Pact} from '@pact-foundation/pact';
import * as path from 'path';
import {HttpClientModule} from '@angular/common/http';
import {term} from '@pact-foundation/pact/dsl/matchers';

describe('Person API', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule
      ],
      providers: [
        PersonClientService
      ]
    });
  });

  const provider: Pact = new Pact({
    port: 1234,
    log: path.resolve(process.cwd(), 'target', 'logs', 'mockserver-integration.log'),
    dir: path.resolve(process.cwd(), 'target', 'pacts'),
    spec: 2,
    logLevel: 'debug',
    pactfileWriteMode: 'update',
    consumer: 'person-frontend',
    provider: 'partner-api',
  });

  beforeAll((done) => {
    provider.setup().then(() => {
      done();
    });
  });

  afterAll((done) => {
    provider.verify()
      .then(() => provider.finalize())
      .then(() => {
        done();
      });
  });

  beforeAll((done) => {
    provider.addInteraction({
      state: '',
      uponReceiving: 'create partner',
      withRequest: {
        method: 'POST',
        path: '/person/rest/api/v1/partners',
        body: {
          name: 'Einstein',
          forename: 'Albert'
        }
      },
      willRespondWith: {
        status: 201,
        body: {
          id: term({
            generate: 'P87654321',
            matcher: 'P[0-9]{8}'
          }),
          name: 'Einstein',
          forename: 'Albert'
        }
      }
    }).then(
      () => {
        done();
      },
      error => done.fail(error)
    );
  });

  it('should answer that the partner is created', (done) => {
    const client: PersonClientService = TestBed.get(PersonClientService);
    client.createPartner({
      name: 'Einstein', forename: 'Albert'
    })
      .subscribe(
        () => {
          done();
        },
        (error) => done.fail(error)
      );
  });

})
;
